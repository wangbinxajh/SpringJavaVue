package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

//@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user")
    List<User> findAll();


    @Insert("INSERT into user(name,age,password) VALUES (#{name},#{age},#{password});")
    int insert(User user);


    int update(User user);


    @Delete("delete  from user  where id = #{id}")
    Integer deleteById(@Param("id") Integer id);


//    @Select("select * from user limit #{pageNum},#{pageSize}")
//    List<User>selectPages(Integer pageNum, Integer pageSize);

    @Select("select count(*) from user ")
    Integer selectTotal();

}
