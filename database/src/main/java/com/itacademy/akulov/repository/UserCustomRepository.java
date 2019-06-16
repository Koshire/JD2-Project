package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> getAllByCustom(FindDto findDto);
    Long getAllByCustomCount(FindDto findDto);
}
