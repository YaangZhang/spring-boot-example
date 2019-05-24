#如何优雅地使用 MyBatis 注解版
###传参方式
####1、直接使用
```
@Delete("DELETE FROM users WHERE id =#{id}")
void delete(Long id);
```
在 SQL 中使用 #{id} 来接收同名参数。
####2、使用 @Param

如果形参有多个，这个注解使用在映射方法的参数上就能为它们取自定义名字。若不给出自定义名字，多参数则先以 "param" 作前缀，再加上它们的参数位置作为参数别名。例如，#{param1}、#{param2}，这个是默认值。如果注解是 @Param("person")，那么参数就会被命名为 #{person}。
```
@Select("SELECT * FROM users WHERE user_sex = #{user_sex}")
List<User> getListByUserSex(@Param("user_sex") String userSex);
```

####3、使用 Map

需要传送多个参数时，可以考虑使用 Map：
```
@Select("SELECT * FROM users WHERE username=#{username} AND user_sex = #{user_sex}")
List<User> getListByNameAndSex(Map<String, Object> map);
```
使用时将参数依次加入到 Map 中即可：
```
Map param=  new HashMap();
param.put("username","aa");
param.put("user_sex","MAN");
List<User> users = userMapper.getListByNameAndSex(param);
```
####4、使用对象

最常用的使用方式是直接使用对象：
```
@Insert("INSERT INTO users(userName,passWord,user_sex) VALUES(#{userName}, #{passWord}, #{userSex})")
void insert(User user);
```
在执行时，系统会自动读取对象的属性并值赋值到同名的 #{xxx} 中。
###@Results 和 @Result 注解
是将数据库中查询到的数值转化为具体的字段，修饰返回的结果集，关联实体类属性和数据库字段一一对应，
比如查询的对象返回值属性名和字段名不一致，或者对象的属性中使用了枚举。
如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。示例如下：
```
@Select("SELECT * FROM users")
@Results({
    @Result(property = "userSex",  column = "user_sex", javaType = UserSexEnum.class),
    @Result(property = "nickName", column = "nick_name")
})
List<UserEntity> getAll();
```
**注意，使用 # 符号和 $ 符号的不同**:
```
// This example creates a prepared statement, something like select * from teacher where name = ?;
@Select("Select * from teacher where name = #{name}")
Teacher selectTeachForGivenName(@Param("name") String name);

// This example creates n inlined statement, something like select * from teacher where name = 'someName';
@Select("Select * from teacher where name = '${name}'")
Teacher selectTeachForGivenName(@Param("name") String name);
```
同上，上面两个例子可以发现，使用 # 会对 SQL 进行预处理，使用 $ 时拼接 SQL，建议使用 #，使用 $ 有 SQL 注入的可能性。
###动态 SQL
####使用注解来实现
用 script 标签包围，然后像 XML 语法一样书写：
```
@Update("<script>
  update users
    <set>
      <if test="userName != null">userName=#{userName},</if>
      <if test="nickName != null">nick_name=#{nickName},</if>
    </set>
  where id=#{id}
</script>")
void update(User user);
```
####使用 SQL 构建类来支持
以分页为例进行演示，首先定义一个 UserSql 类，提供方法拼接需要执行的 SQL：
```
public class UserSql {
    public String getList(UserParam userParam) {
        StringBuffer sql = new StringBuffer("select id, userName, passWord, user_sex as userSex, nick_name as nickName");
        sql.append(" from users where 1=1 ");
        if (userParam != null) {
            if (StringUtils.isNotBlank(userParam.getUserName())) {
                sql.append(" and userName = #{userName}");
            }
            if (StringUtils.isNotBlank(userParam.getUserSex())) {
                sql.append(" and user_sex = #{userSex}");
            }
        }
        sql.append(" order by id desc");
        sql.append(" limit " + userParam.getBeginLine() + "," + userParam.getPageSize());
        log.info("getList sql is :" +sql.toString());
        return sql.toString();
    }
}
```
接下来只需要在 Mapper 中引入这个类和方法即可。
```
@SelectProvider(type = UserSql.class, method = "getList")
List<UserEntity> getList(UserParam userParam);
```
* type：动态生成 SQL 的类
* method：类中具体的方法名

相对于 @SelectProvider 提供查询 SQL 方法导入，还有 @InsertProvider、@UpdateProvider、@DeleteProvider 提供给插入、更新、删除的时候使用。

####结构化 SQL
可能你会觉得这样拼接 SQL 很麻烦，SQL 语句太复杂也比较乱，别着急！MyBatis 给我们提供了一种升级的方案：结构化 SQL。

示例如下：
```
public String getCount(UserParam userParam) {
   String sql= new SQL(){{
        SELECT("count(1)");
        FROM("users");
        if (StringUtils.isNotBlank(userParam.getUserName())) {
            WHERE("userName = #{userName}");
        }
        if (StringUtils.isNotBlank(userParam.getUserSex())) {
            WHERE("user_sex = #{userSex}");
        }
        //从这个 toString 可以看出，其内部使用高效的 StringBuilder 实现 SQL 拼接
    }}.toString();

    log.info("getCount sql is :" +sql);
    return sql;
}
```
* SELECT 表示要查询的字段，可以写多行，多行的 SELECT 会智能地进行合并而不会重复。

* FROM 和 WHERE 跟 SELECT 一样，可以写多个参数，也可以在多行重复使用，最终会智能合并而不会报错。这样语句适用于写很长的 SQL，且能够保证 SQL 结构清楚，便于维护、可读性高。


具体使用和 XML 版本一致，直接注入到使用的类中即可。
