package com.ling.dao.entity;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)

public class Student implements Serializable {
    /** */
    private Integer id;

    /** 年龄*/
    @NotNull
    private Integer age;

    /** 姓名*/
    @NotEmpty(message = "狗的姓名不能为空")
    private String dogName;

    /** */
    private String needBook;

    /** */
    private Integer needmoney;

    /** */
    private String teacher;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName == null ? null : dogName.trim();
    }

    public String getNeedBook() {
        return needBook;
    }

    public void setNeedBook(String needBook) {
        this.needBook = needBook == null ? null : needBook.trim();
    }

    public Integer getNeedmoney() {
        return needmoney;
    }

    public void setNeedmoney(Integer needmoney) {
        this.needmoney = needmoney;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher == null ? null : teacher.trim();
    }
}