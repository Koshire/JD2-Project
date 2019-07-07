package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.TeacherDto;
import com.itacademy.akulov.mapper.TeacherMapper;
import com.itacademy.akulov.repository.TeacherUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class TeacherUserSerice implements BaseService<TeacherDto, Long> {

    private TeacherUserRepository teachRepo;
    private TeacherMapper teacherMapper = TeacherMapper.getInstance();

    @Autowired
    public TeacherUserSerice(TeacherUserRepository teachRepo) {
        this.teachRepo = teachRepo;
    }

    @Override
    public Optional<TeacherDto> getById(Long aLong) {
        TeacherDto teacherDto = null;
        if (teachRepo.findById(aLong).isPresent()) {
            teacherDto = teacherMapper.mapToDto(teachRepo.findById(aLong).get());
        }
        return Optional.ofNullable(teacherDto);
    }
}
