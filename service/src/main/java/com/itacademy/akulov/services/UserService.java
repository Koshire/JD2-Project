package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.StudentUserRepository;
import com.itacademy.akulov.repository.TeacherUserRepository;
import com.itacademy.akulov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final LoginMapper loginMapper = LoginMapper.getInstance();

    private UserRepository userRepository;
    private StudentUserRepository studentUserRepository;
    private TeacherUserRepository teacherUserRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       StudentUserRepository studentUserRepository,
                       TeacherUserRepository teacherUserRepository) {

        this.userRepository = userRepository;
        this.studentUserRepository = studentUserRepository;
        this.teacherUserRepository = teacherUserRepository;
    }

    public Optional<LoginDto> getById(Long id) {
        return userRepository.findById(id).map(loginMapper::mapToDto);
    }

    public boolean deleteById(Long id) {
        Long result = 0L;
        if (userRepository.findById(id).isPresent()) {
            userRepository.delete(userRepository.findById(id).get());
            result = 1L;
        }
        return result > 0;
    }

    public List<LoginDto> getAll() {
        List<LoginDto> list = new ArrayList<>();
        userRepository.findAll().forEach(user -> {
            list.add(loginMapper.mapToDto(user));
        });
        return list;
    }

    public Optional<LoginDto> getLogin(String email) {
        return userRepository.findByEmail(email).map(loginMapper::mapToDto);
    }

    public Long saveStudentUser(LoginDto loginDto) {
        return studentUserRepository.save(loginMapper.mapToStudentEntity(loginDto)).getId();
    }

    public Long updateUser(LoginDto loginDto) {
        Long id = 0L;
        if (userRepository.findById(loginDto.getId()).isPresent()) {
            User user = userRepository.findById(loginDto.getId()).get();
            user = loginMapper.mapToEntity(loginDto);
            id = userRepository.save(user).getId();
        }
        return id;
    }

    public Long saveTeacherUser(LoginDto loginDto) {
        return teacherUserRepository.save(loginMapper.mapToTeacherEntity(loginDto)).getId();
    }

    public Boolean checkUser(String login) {
        return userRepository.findByEmail(login).isPresent();
    }
}
