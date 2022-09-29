package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiec extends ServiceImpl<UserMapper,User> {
    public boolean saveUser(User user) {
        if (user.getId() == null){
            return save(user);
        }else {
            return updateById(user);
        }

    }

//    @Autowired
//    private UserMapper userMapper;
//
//    public int save(User user){
//        if (user.getId() == null){
//            return userMapper.insert(user);
//        }else {
//            return userMapper.update(user);
//        }
//    }
}
