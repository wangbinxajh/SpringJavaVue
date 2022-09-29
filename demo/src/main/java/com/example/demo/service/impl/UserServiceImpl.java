package com.example.demo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.common.Constants;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.exception.ServiceException;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.apache.xmlbeans.impl.xb.xsdschema.Attribute;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService{

    private static final Log LOG = Log.get();
    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("name",userDTO.getName());
//        queryWrapper.eq("password",userDTO.getPassword());
//        User one;
//        try {
//            one = getOne(queryWrapper); //数据库用户信息
//        }catch (Exception e){
//            LOG.error(e);
//            throw new  ServiceException(Constants.CODE_500,"系统错误");
//        }
        if ( one != null){
            BeanUtil.copyProperties(one,userDTO,true);
            return  userDTO;
        }else {
            throw new  ServiceException(Constants.CODE_600,"用户名错误");
        }
    }

    @Override
    public boolean saveUser(User user) {
        if (user.getId() == null){
            return save(user);
        }else {
            return updateById(user);
        }
    }

    @Override
    public User register(UserDTO userDTO){
        User one = getUserInfo(userDTO);
        if (one == null){
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            System.out.println("----------------"+ one);
            save(one);
        }else {
            throw new  ServiceException(Constants.CODE_600,"用户名已存在");
        }
        return  one;
    }

    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",userDTO.getName());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try {
            one = getOne(queryWrapper); //数据库用户信息
        }catch (Exception e){
            LOG.error(e);
            throw new  ServiceException(Constants.CODE_500,"系统错误");
        }
        return one;
    }


}
