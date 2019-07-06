package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.StudentUserRepository;
import com.itacademy.akulov.repository.TeacherUserRepository;
import com.itacademy.akulov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public Optional<LoginDto> getById(Long id){
        return userRepository.findById(id).map(loginMapper::mapToDto);
    }

    public Optional<LoginDto> getLogin(String email) {
        return userRepository.findByEmail(email).map(loginMapper::mapToDto);
    }

    public Long saveStudentUser(LoginDto loginDto) {
        return studentUserRepository.save(loginMapper.mapToStudentEntity(loginDto)).getId();
    }

    public Long updateUser(LoginDto loginDto) {
        return userRepository.save(loginMapper.mapToStudentEntity(loginDto)).getId();
    }

    public Long saveTeacherUser(LoginDto loginDto) {
        return teacherUserRepository.save(loginMapper.mapToTeacherEntity(loginDto)).getId();
    }

    public Boolean checkUser(String login) {
        return userRepository.findByEmail(login).isPresent();
    }
}
