package com.ling.gms.test.service.serviceImple;

import com.ling.gms.test.dao.entity.People;
import com.ling.gms.test.dao.mapper.PeopleMapper;
import com.ling.gms.test.service.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author TianHeLing
 * @Description
 * @date 2019/8/20
 */
@Service
public class PeopleServiceImple implements PeopleService {

    @Autowired
    private PeopleMapper peopleMapper;

    @Override
    @Transactional
    public People getPeopleById(Integer id) {
        People people = peopleMapper.selectByPrimaryKey(id);
        return people;
    }
}
