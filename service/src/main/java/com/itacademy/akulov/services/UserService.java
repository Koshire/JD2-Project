package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.UserCustomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final LoginMapper loginMapper = LoginMapper.getInstance();

    private UserCustomRepository userCustomRepository;

    @Autowired
    public UserService(UserCustomRepository userCustomRepository) {
        this.userCustomRepository = userCustomRepository;
    }

    public Long getSizeByCustom(FindDto findDto) {
        return userCustomRepository.getAllByCustomCount(findDto);
    }

    public List<LoginDto> getByCustomLO(FindDto findDto) {
        List<User> list = userCustomRepository.getAllByCustom(findDto);
        return list.stream().map(loginMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
