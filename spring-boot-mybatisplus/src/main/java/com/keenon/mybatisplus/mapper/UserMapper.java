package com.keenon.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.keenon.mybatisplus.entity.UserEntity;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhy
 * @since 2021-04-16
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    @Select("select * from user where name = #{name}")
    public UserEntity getUserByName(String name);

    public UserEntity getUserByEmail(String email);
}
