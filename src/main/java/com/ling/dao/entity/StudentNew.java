package com.ling.dao.entity;

import javax.persistence.*;

@Table(name = "student")
public class StudentNew {
    private Integer id;

    private String name;

    private Float age;

    @Column(name = "class_room")
    private Integer classRoom;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return age
     */
    public Float getAge() {
        return age;
    }

    /**
     * @param age
     */
    public void setAge(Float age) {
        this.age = age;
    }

    /**
     * @return class_room
     */
    public Integer getClassRoom() {
        return classRoom;
    }

    /**
     * @param classRoom
     */
    public void setClassRoom(Integer classRoom) {
        this.classRoom = classRoom;
    }
}