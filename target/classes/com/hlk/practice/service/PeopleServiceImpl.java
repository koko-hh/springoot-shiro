package com.hlk.practice.service;

import com.hlk.practice.mapper.PeopleMapper;
import com.hlk.practice.pojo.People;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeopleServiceImpl implements PeopleService {
   @Autowired
    PeopleMapper peopleMapper;
    @Override
    public int addPeople(People people) {
        return peopleMapper.addPeople(people);
    }

    @Override
    public int remove(Integer id) {
        return peopleMapper.remove(id);
    }

    @Override
    public People update(People people) {
        return peopleMapper.update(people);
    }

    @Override
    public List<People> getAllPeople() {
        return peopleMapper.getAllPeople();
    }

    @Override
    public People getPeople(Integer id) {
        return peopleMapper.getPeople(id);
    }

    @Override
    public People getPeopleIndex(String name, String pwd) {
        return peopleMapper.getPeopleIndex(name,pwd);
    }

    @Override
    public People getPeopleByName(String name) {
        return peopleMapper.getPeopleByName(name);
    }
}
