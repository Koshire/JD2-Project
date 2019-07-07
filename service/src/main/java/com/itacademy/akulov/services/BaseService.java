package com.itacademy.akulov.services;

import java.util.Optional;

public interface BaseService<T, U> {

    Optional<T> getById(U u);

}
