package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.LoginDto;
import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.TeacherUser;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.mapper.StudentMapper;
import com.itacademy.akulov.mapper.TeacherMapper;
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
    private StudentMapper studentMapper = StudentMapper.getInstance();
    private TeacherMapper teacherMapper = TeacherMapper.getInstance();

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

    public boolean blockById(Long id) {
        Long result = 0L;
        if (userRepository.findById(id).isPresent()) {
            User check = userRepository.findById(id).get();
            if (check.getRole().equals(Role.STUDY)) {
                StudentUser studentUser = studentUserRepository.findById(id).get();
                if (studentUser.getBlockList()) {
                    studentUser.setBlockList(false);
                } else {
                    studentUser.setBlockList(true);
                }
                result = studentUserRepository.save(studentUser).getId();
            } else if (check.getRole().equals(Role.TEACH)) {
                TeacherUser teacherUser = teacherUserRepository.findById(id).get();
                if (teacherUser.getBlockList()) {
                    teacherUser.setBlockList(false);
                } else {
                    teacherUser.setBlockList(true);
                }
                result = teacherUserRepository.save(teacherUser).getId();
            } else {
                User user = userRepository.findById(id).get();
                if (user.getBlockList()) {
                    user.setBlockList(false);
                } else {
                    user.setBlockList(true);
                }
                result = userRepository.save(user).getId();
            }
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
        Long result = 0L;
        if (userRepository.findById(loginDto.getId()).isPresent()) {
            User check = userRepository.findById(loginDto.getId()).get();
            if (check.getRole().equals(Role.STUDY)) {
                StudentUser studentUser = studentUserRepository.findById(loginDto.getId()).get();
                studentUser = studentMapper.mapToEntity(loginDto);
                result = studentUserRepository.save(studentUser).getId();
            } else if (check.getRole().equals(Role.TEACH)) {
                TeacherUser teacherUser = teacherUserRepository.findById(loginDto.getId()).get();
                teacherUser = teacherMapper.mapToEntity(loginDto);
                result = teacherUserRepository.save(teacherUser).getId();
            } else {
                User user = userRepository.findById(loginDto.getId()).get();
                user = loginMapper.mapToEntity(loginDto);
                result = userRepository.save(user).getId();
            }
        }
        return result;
    }

    public Long saveTeacherUser(LoginDto loginDto) {
        return teacherUserRepository.save(loginMapper.mapToTeacherEntity(loginDto)).getId();
    }

    public Boolean checkUser(String login) {
        return userRepository.findByEmail(login).isPresent();
    }
}
