package com.sto.repository.test1;

import com.sto.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 创建的 Repository 只要继承 JpaRepository 即可
 * 可以根据方法名自动生产 SQL，比如 findByUserName 会自动生产一个以 userName 为参数的查询方法，
 * 比如 findAll 会自动查询表里面的所有数据等
 */
public interface User1Repository extends JpaRepository<User,Long> {
//    基本查询
    User findByUserName(String userName);
    User findByUserNameOrEmail(String username, String email);


//    自定义 SQL 查询
    /**
     * 在 SQL 的查询方法上面使用 @Query 注解，在注解内写 Hql 来查询内容。
     */
    @Query("select u from User u")
    Page<User> findALL(Pageable pageable);

    /**
     * 使用原生 SQL 也是支持的，需要再添加一个参数 nativeQuery = true。
     * @param nickName
     * @param pageable
     * @return
     */
    @Query(value = "select * from user u where u.nick_name = ?1", nativeQuery = true)
    Page<User> findByNickName(String nickName, Pageable pageable);

    /**
     * @Query 上面的 1 代表的是方法参数里面的顺序，如果有多个参数也可以按照这个方式添加 1、2、3....。
     *      * 除了按照这种方式传参外，还可以使用 @Param 来支持。
     * @param userName
     * @param pageable
     * @return
     */
    @Query("select u from User u where u.userName = :userName")
    Page<User> findByUserName(@Param("userName") String userName, Pageable pageable);

    /**
     * 涉及到删除和修改需要加上 @Modifying，也可以根据需要添加 @Transactional 对事务的支持、操作超时设置等。
     */
    @Transactional(timeout = 10)
    @Modifying
    @Query("update User set userName = ?1 where id = ?2")
    int modifyById(String userName, Long id);

    @Transactional
    @Modifying
    @Query("delete from User where id = ?1")
    void deleteById(Long id);

//    使用已命名的查询
    List<User> findByPassWord(String passWord);
    List<User> findByNickName(String nickName);
}