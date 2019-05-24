package com.sto.mybatis.mapper.test2;

import com.sto.mybatis.enums.UserSexEnum;
import com.sto.mybatis.model.User;
import com.sto.mybatis.param.UserParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Results 和 @Result 注解
 *
 * 这两个注解配合来使用，主要作用是将数据库中查询到的数值转化为具体的字段，修饰返回的结果集，
 * 关联实体类属性和数据库字段一一对应，如果实体类属性和数据库属性名保持一致，就不需要这个属性来修饰。
 *
 * 使用 # 符号和 $ 符号的不同： # 会对 SQL 进行预处理，使用 $ 时拼接 SQL，建议使用 #，使用 $ 有 SQL 注入的可能性
 */
public interface User2Mapper {

    @Select("select * from users")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nikeName", column = "nick_name")
    })
    List<User> getAll();

    @Select("SELECT * FROM users WHERE id = #{id}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nikeName", column = "nick_name")
    })
    User getOne(Long id);

    List<User> getList(UserParam userParam);

    int getCount(UserParam userParam);

    @Insert("insert into users(userName,passWord,user_sex) values(#{userName}, #{passWord}, #{userSex})")
    void insert(User user);

    @Update("update users set userName=#{userName},passWord=#{passWord}, user_sex=#{userSex},nick_name=#{nikeName} where id=#{id}")
    void update(User user);

    @Delete("delete from users where id = #{id}")
    void delete(Long id);

    /**
     * @param userSex
     * @return
     * @Param 注解使用在映射方法的参数上, 为它们取自定义名字
     * # 符号和 $ 符号的不同：
     *  # 会对 SQL 进行预处理，使用 $ 时拼接 SQL，建议使用 #，使用 $ 有 SQL 注入的可能性。
     * This example creates n inlined statement, something like select * from users where user_sex = ?;
     */
    @Select("SELECT * FROM users WHERE user_sex = #{user_sex}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nikeName", column = "nick_name")
    })
    List<User> getListByUserSex(@Param("user_sex") String userSex);

    // This example creates n inlined statement, something like select * from users where userName = 'someName';
    @Select("Select * from users where userName = '${name}'")
    User selectTeachForGivenName(@Param("name") String name);

    /**
     * 需要传送多个参数时，可以考虑使用 Map
     *
     * @param map
     * @return
     */
    @Select("SELECT * FROM users WHERE username=#{username} AND user_sex = #{user_sex}")
    @Results({
            @Result(property = "userSex", column = "user_sex", javaType = UserSexEnum.class),
            @Result(property = "nikeName", column = "nick_name")
    })
    List<User> getListByNameAndSex(Map<String, Object> map);



}