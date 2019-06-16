package com.itacademy.akulov.repository;

import com.itacademy.akulov.config.TestConfiguration;
import com.itacademy.akulov.dto.FindDto;
import com.itacademy.akulov.utils.ItemsCreator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TestConfiguration.class)
@Transactional
public class UserRepositoryTest {

    @Autowired
    private ItemsCreator itemsCreator;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @Before
    public void init() {
        itemsCreator.prepareData();
    }

    @Test
    public void get() {

        FindDto findDto = FindDto.builder()
                .page(1L)
                .pages(1L)
                .viewLimit(5L)
                .build();
        userCustomRepository.getAllByCustomCount(findDto);

        userCustomRepository.getAllByCustom(findDto);

    }

}