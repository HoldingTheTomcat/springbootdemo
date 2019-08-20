package com.ling.gms.test.controller;

import com.ling.gms.test.dao.entity.People;
import com.ling.gms.test.service.PeopleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/8/20
 */
@RestController
@Slf4j
@RequestMapping("people")
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @GetMapping("test")
    public People testPeople(){
        People peopleById = peopleService.getPeopleById(1);
        return peopleById;
    }
}
