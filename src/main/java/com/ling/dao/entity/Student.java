package com.ling.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;

@ApiModel(value = "学生信息接收对象", description = "接收学生age、name")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Integer id;

    /**
     * 年龄
     */
    @ApiModelProperty(value = "学生年龄", name = "dogAge",allowEmptyValue = true)
    @Column(name = "dog_age")
    private Integer dogAge;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "学生姓名", name = "dogNameNew",example = "旺仔",required = true)
    @Column(name = "dog_name_new")
    private String dogNameNew;

    
    @Column(name = "need_book")
    private String needBook;

    @Column(name = "needMoney")
    private Integer needmoney;

    private String teacher;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取年龄
     *
     * @return dog_age - 年龄
     */
    public Integer getDogAge() {
        return dogAge;
    }

    /**
     * 设置年龄
     *
     * @param dogAge 年龄
     */
    public void setDogAge(Integer dogAge) {
        this.dogAge = dogAge;
    }

    /**
     * 获取姓名
     *
     * @return dog_name_new - 姓名
     */
    public String getDogNameNew() {
        return dogNameNew;
    }

    /**
     * 设置姓名
     *
     * @param dogNameNew 姓名
     */
    public void setDogNameNew(String dogNameNew) {
        this.dogNameNew = dogNameNew;
    }

    /**
     * @return need_book
     */
    public String getNeedBook() {
        return needBook;
    }

    /**
     * @param needBook
     */
    public void setNeedBook(String needBook) {
        this.needBook = needBook;
    }

    /**
     * @return needMoney
     */
    public Integer getNeedmoney() {
        return needmoney;
    }

    /**
     * @param needmoney
     */
    public void setNeedmoney(Integer needmoney) {
        this.needmoney = needmoney;
    }

    /**
     * @return teacher
     */
    public String getTeacher() {
        return teacher;
    }

    /**
     * @param teacher
     */
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}