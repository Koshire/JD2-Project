package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.KnowlegeBaseDto;
import com.itacademy.akulov.entity.KnowlegeBase;
import com.itacademy.akulov.mapper.CourseMapper;
import com.itacademy.akulov.mapper.KnowlegeBaseMapper;
import com.itacademy.akulov.mapper.LoginMapper;
import com.itacademy.akulov.repository.CourseRepository;
import com.itacademy.akulov.repository.KnowlegeBaseRepository;
import com.itacademy.akulov.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class KnowlegeBaseService {

    private KnowlegeBaseRepository knowlegeBaseRepository;
    private LoginMapper loginMapper = LoginMapper.getInstance();
    private CourseMapper courseMapper = CourseMapper.INSTANCE;
    private UserRepository userRepository;
    private CourseRepository courseRepository;
    private KnowlegeBaseMapper knowlegeBaseMapper = KnowlegeBaseMapper.getInstance();

    @Autowired
    public KnowlegeBaseService(KnowlegeBaseRepository knowlegeBaseRepository,
                               UserRepository userRepository,
                               CourseRepository courseRepository) {
        this.knowlegeBaseRepository = knowlegeBaseRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public List<KnowlegeBaseDto> getAllKb() {
        List<KnowlegeBaseDto> list = new ArrayList<>();
        knowlegeBaseRepository.findAll().forEach(knowlegeBase -> {
            list.add(knowlegeBaseMapper.mapToDto(knowlegeBase));
        });
        return list;
    }

    public Long createKb(KnowlegeBaseDto knowlegeBaseDto) {
        Long id = 0L;
        if (userRepository
                .findById(knowlegeBaseDto.getUserKnowlegeBase().getId()).isPresent()
                && courseRepository.findById(knowlegeBaseDto.getCourseDtoC().getId()).isPresent()) {
            id = knowlegeBaseRepository.save(KnowlegeBase.builder()
                    .userKnowlegeBase(userRepository
                            .findById(knowlegeBaseDto.getUserKnowlegeBase().getId()).get())
                    .course(courseRepository.findById(knowlegeBaseDto.getCourseDtoC().getId()).get())
                    .text(knowlegeBaseDto.getText())
                    .localDate(LocalDate.parse(knowlegeBaseDto.getLocalDate()))
                    .build()).getId();
        }
        return id;
    }

    public KnowlegeBaseDto convertById(Long id) {
        KnowlegeBaseDto knowlegeBaseDto = KnowlegeBaseDto.builder()
                .id(0L)
                .build();

        if (knowlegeBaseRepository.findById(id).isPresent()) {
            knowlegeBaseDto = knowlegeBaseMapper
                    .mapToDto(knowlegeBaseRepository.findById(id).get());
        }
        return knowlegeBaseDto;
    }

    public boolean delete(Long id) {
        long result = 0L;
        if (knowlegeBaseRepository.existsById(id)) {
            knowlegeBaseRepository.deleteById(id);
            result = 1L;
        }
        return result > 0;
    }
}
