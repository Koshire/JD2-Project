package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.StudentDto;
import com.itacademy.akulov.mapper.StudentMapper;
import com.itacademy.akulov.repository.StudentUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class StudentUserService implements BaseService<StudentDto, Long> {

    private StudentUserRepository studyRep;
    private StudentMapper studentMapper = StudentMapper.getInstance();

    @Autowired
    public StudentUserService(StudentUserRepository studyRep) {
        this.studyRep = studyRep;
    }

    @Override
    public Optional<StudentDto> getById(Long aLong) {
        StudentDto studentDto = null;
        if (studyRep.findById(aLong).isPresent()) {
            studentDto = studentMapper.mapToDto(studyRep.findById(aLong).get());
        }
        return Optional.ofNullable(studentDto);
    }
}
