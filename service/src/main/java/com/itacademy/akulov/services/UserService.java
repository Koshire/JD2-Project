package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final LoginMapper loginMapper = LoginMapper.getInstance();

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Long getSizeByCustom(PaginationDto paginationDto) {
        return userRepository.getAllByCustomCount(paginationDto);
    }

    public List<LoginDto> getByCustomLO(PaginationDto paginationDto) {
        return userRepository.getAllByCustom(paginationDto).stream().map(loginMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public Optional<LoginDto> getLogin(String email) {
        return userRepository.findByEmail(email).map(loginMapper::mapToDto);
    }
}
