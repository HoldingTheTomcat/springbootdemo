package com.ling.dao.entity;

import javax.persistence.*;

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 姓名
     */
    @Column(name = "dog_name")
    private String dogName;

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
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取姓名
     *
     * @return dog_name - 姓名
     */
    public String getDogName() {
        return dogName;
    }

    /**
     * 设置姓名
     *
     * @param dogName 姓名
     */
    public void setDogName(String dogName) {
        this.dogName = dogName;
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