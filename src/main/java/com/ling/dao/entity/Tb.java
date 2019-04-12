package com.ling.dao.entity;

import javax.persistence.*;

public class Tb {
    private String 姓名;

    private String 课程;

    private Integer 分数;

    /**
     * @return 姓名
     */
    public String get姓名() {
        return 姓名;
    }

    /**
     * @param 姓名
     */
    public void set姓名(String 姓名) {
        this.姓名 = 姓名;
    }

    /**
     * @return 课程
     */
    public String get课程() {
        return 课程;
    }

    /**
     * @param 课程
     */
    public void set课程(String 课程) {
        this.课程 = 课程;
    }

    /**
     * @return 分数
     */
    public Integer get分数() {
        return 分数;
    }

    /**
     * @param 分数
     */
    public void set分数(Integer 分数) {
        this.分数 = 分数;
    }
}