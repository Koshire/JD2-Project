package com.itacademy.akulov.repository;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.User;

import java.util.List;

public interface UserCustomRepository {

    List<User> getAllByCustom(PaginationDto paginationDto);

    Long getAllByCustomCount(PaginationDto paginationDto);
}
