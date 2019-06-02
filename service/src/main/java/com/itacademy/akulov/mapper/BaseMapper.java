package com.itacademy.akulov.mapper;

public interface BaseMapper<E, D> {

    D mapToDto(E entity);

    E mapToEntity(D dto);
}
