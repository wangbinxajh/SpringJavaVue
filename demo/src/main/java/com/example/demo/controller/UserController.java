package com.example.demo.controller;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.example.demo.common.Constants;
import com.example.demo.common.Result;
import com.example.demo.controller.dto.UserDTO;
import com.example.demo.entity.User;

import com.example.demo.service.IUserService;
import com.example.demo.service.UserServiec;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import java.io.InputStream;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    @Resource
    private IUserService userService;

    @GetMapping
    public Result findAll(){
        return Result.success(userService.list());
    }

    @GetMapping("/page")
    public  Result findPage(@RequestParam Integer pageNum,
                                      @RequestParam Integer pageSize,
                                      @RequestParam(defaultValue = "") String name,
                               @RequestParam(defaultValue = "")String hobbyPic){
        IPage<User> page = new Page<>(pageNum,pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (!"".equals(name)){
            queryWrapper.like("name",name);
        }
        if (!"".equals(hobbyPic)){
            queryWrapper.like("hobby_pic",hobbyPic);
        }
        queryWrapper.orderByDesc("id");
        return Result.success( userService.page(page,queryWrapper));
    }

    @PostMapping
    public Result save(@RequestBody User user){
        return Result.success(userService.saveUser(user));
    }


    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO){
        String password = userDTO.getPassword();
        String username = userDTO.getName();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400,"????????????");
        }
        UserDTO dto =  userService.login(userDTO);
        return Result.success(dto) ;
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO){
        String password = userDTO.getPassword();
        String username = userDTO.getName();
        if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            return Result.error(Constants.CODE_400,"????????????");
        }
        return Result.success(userService.register(userDTO));
    }


    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){

        return Result.success(userService.removeById(id));
    }


    @GetMapping("/name/{name}")
    public Result findOne(@PathVariable String name){
        QueryWrapper<User>  queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return Result.success(userService.getOne(queryWrapper));
    }

    @PostMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer>ids){
        return Result.success(userService.removeByIds(ids));
    }


    /**
     * ??????excel
     * @param response
     * @throws Exception
     */
    @GetMapping("/export")
    public void export(HttpServletResponse response) throws Exception{
        List<User> list = userService.list();
//        ExcelWriter excelWriter = ExcelUtil.getWriter("????????????URL");

        ExcelWriter writer = ExcelUtil.getWriter(true);

        //??????excel?????????
        writer.addHeaderAlias("id","??????");
        writer.addHeaderAlias("name","??????");
        writer.addHeaderAlias("age","??????");
        writer.addHeaderAlias("hobbyPic","??????");

        writer.write(list,true);

        //????????????????????????????????????????????????
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String fileName = URLEncoder.encode("????????????","UTF-8");
        response.setHeader("Content-Disposition","attachment;fileName=" + fileName + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out,true);
        out.close();
        writer.close();

    }

    /**
     * ??????excel
     * @param file
     * @throws Exception
     */
    @PostMapping("/import")
    public Result imp(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        ExcelReader reader = ExcelUtil.getReader(inputStream);
        List<User> list = reader.readAll(User.class);
//        System.out.println(list);
        userService.saveBatch(list);
        return Result.success(true);
    }
}
