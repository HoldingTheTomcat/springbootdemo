package com.ling.dao.entity;

import javax.persistence.*;

public class Teacher {
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
    private String name;

    @Column(name = "left_id")
    private Integer leftId;

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
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return left_id
     */
    public Integer getLeftId() {
        return leftId;
    }

    /**
     * @param leftId
     */
    public void setLeftId(Integer leftId) {
        this.leftId = leftId;
    }
}