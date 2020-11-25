package com.hlk.practice.mapper;

import com.hlk.practice.pojo.People;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PeopleMapper {
    int addPeople(People people);
    int remove(Integer id);
    People update(People people);
    List<People> getAllPeople();
    People getPeople(Integer id);
    People getPeopleIndex(String name,String pwd);
    People getPeopleByName(String name);
}
