package com.itacademy.akulov.utils;

import com.itacademy.akulov.entity.Role;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.TeacherUser;
import com.itacademy.akulov.entity.User;
import com.itacademy.akulov.entity.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@Component
public class ItemsCreator {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public ItemsCreator(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public void prepareData() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        StudentUser firstUserS = StudentUser.builder()
                .email("firstUserS@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("firstUserS")
                        .lastName("firstUserS")
                        .middleName("firstUserS")
                        .build())
                .build();

        StudentUser secondUserS = StudentUser.builder()
                .email("secondUserS@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("secondUserS")
                        .lastName("secondUserS")
                        .middleName("secondUserS")
                        .build())
                .build();

        StudentUser thirdUserS = StudentUser.builder()
                .email("thirdUserS@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("thirdUserS")
                        .lastName("thirdUserS")
                        .middleName("thirdUserS")
                        .build())
                .build();

        TeacherUser firstUserT = TeacherUser.builder()
                .email("firstUserT@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("firstUserT")
                        .lastName("firstUserT")
                        .middleName("firstUserT")
                        .build())
                .build();

        TeacherUser secondUserT = TeacherUser.builder()
                .email("secondUserT@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("secondUserT")
                        .lastName("secondUserT")
                        .middleName("secondUserT")
                        .build())
                .build();

        TeacherUser thirdUserT = TeacherUser.builder()
                .email("thirdUserT@gmail.com")
                .blockList(Boolean.FALSE)
                .password("123")
                .role(Role.STUDY)
                .userData(UserData.builder()
                        .firstName("thirdUserT")
                        .lastName("thirdUserT")
                        .middleName("thirdUserT")
                        .build())
                .build();

        User admin = new User(null, "admin@gmail.com", "123",
                Role.ADMIN,
                UserData.builder()
                        .firstName("admin")
                        .middleName("admin")
                        .lastName("admin")
                        .build(),
                false);
        entityManager.persist(firstUserS);
        entityManager.persist(secondUserS);
        entityManager.persist(thirdUserS);
        entityManager.persist(admin);
        entityManager.persist(firstUserT);
        entityManager.persist(secondUserT);
        entityManager.persist(thirdUserT);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}