package com.kaoshi.tyg.mapper;


import com.kaoshi.tyg.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    List<User> findAll();

}
