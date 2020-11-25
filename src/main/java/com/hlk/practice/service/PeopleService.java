package com.hlk.practice.service;

import com.hlk.practice.pojo.People;

import java.util.List;

public interface PeopleService {
     int addPeople(People people);
     int remove(Integer id);
     People update(People people);
     List<People> getAllPeople();
     People getPeople(Integer id);
     People getPeopleIndex(String name,String pwd);
     People getPeopleByName(String name);
}
