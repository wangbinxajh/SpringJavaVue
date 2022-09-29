package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

//@data 这个代替了下面的get()/set()方法， @data 这些都是lombok插件
@Data
@TableName("user")
@ToString
public class User implements Serializable {
    @TableId
    private Integer id;
    private String name;
    private Integer age;
    private String password;

//    @TableField(value = "hobby_pic") 数据库字段，
//    和数据库字段可以不同，但是上面字段必须是数据库字段写法，相当于映射，数据库名字可以和实体类不同
    private String hobbyPic;

//    @JsonIgnore     忽略这个字段，不更新
//    private String password;
//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public Integer getAge() {
//        return age;
//    }
//
//    public void setAge(Integer age) {
//        this.age = age;
//    }


}
