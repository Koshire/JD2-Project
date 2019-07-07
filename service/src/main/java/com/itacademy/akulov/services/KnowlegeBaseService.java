package com.itacademy.akulov.services;

import com.itacademy.akulov.dto.KnowlegeBaseDto;
import com.itacademy.akulov.mapper.KnowlegeBaseMapper;
import com.itacademy.akulov.repository.KnowlegeBaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class KnowlegeBaseService {

    private KnowlegeBaseRepository knowlegeBaseRepository;
    private KnowlegeBaseMapper knowlegeBaseMapper = KnowlegeBaseMapper.getInstance();

    @Autowired
    public KnowlegeBaseService(KnowlegeBaseRepository knowlegeBaseRepository) {
        this.knowlegeBaseRepository = knowlegeBaseRepository;
    }

    public List<KnowlegeBaseDto> getAllKb(){
        List<KnowlegeBaseDto> list = new ArrayList<>();
        knowlegeBaseRepository.findAll().forEach(knowlegeBase -> {
            list.add(knowlegeBaseMapper.mapToDto(knowlegeBase));
        });
        return list;
    }
}
