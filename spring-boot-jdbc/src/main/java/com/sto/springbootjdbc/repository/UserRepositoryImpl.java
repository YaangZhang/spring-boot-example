package com.sto.springbootjdbc.repository;

import com.sto.springbootjdbc.modle.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 使用多数据源
 * 增加一个参数为 JdbcTemplate，如果方法中传输了 JdbcTemplate，方法内就会使用传递的 JdbcTemplate 进行操作，
 * 如果传递的 JdbcTemplate 为空，使用默认的 JdbcTemplate 连接操作。
 */
@Repository
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private JdbcTemplate primaryJdbcTemplate;

    @Override
    public int save(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("INSERT INTO users(userName, passWord, user_sex) values(?, ?, ?)",
                user.getUserName(), user.getPassWord(), user.getUserSex());
    }

    @Override
    public int update(User user,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("UPDATE users SET userName = ? , passWord = ? , user_sex = ? WHERE id=?",
                user.getUserName(), user.getPassWord(), user.getUserSex(), user.getId());
    }

    @Override
    public int delete(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.update("DELETE FROM users where id = ? ",id);
    }

    @Override
    public List<User> findALL(JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.query("SELECT * FROM users", new UserRowMapper());
//         return jdbcTemplate.query("SELECT * FROM users", new BeanPropertyRowMapper(User.class));
    }

    @Override
    public User findById(long id,JdbcTemplate jdbcTemplate) {
        if(jdbcTemplate == null){
            jdbcTemplate= primaryJdbcTemplate;
        }
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id=?", new Object[] { id },
                new BeanPropertyRowMapper<User>(User.class));
    }

    class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user = new User();
            user.setId(rs.getLong("id"));
            user.setUserName(rs.getString("userName"));
            user.setPassWord(rs.getString("passWord"));
            user.setUserSex(rs.getString("user_sex"));
            return user;
        }
    }
}