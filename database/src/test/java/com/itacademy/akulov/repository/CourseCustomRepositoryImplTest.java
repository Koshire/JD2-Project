package com.itacademy.akulov.repository;

import com.itacademy.akulov.config.PersistenceConfig;
import com.itacademy.akulov.dto.PaginationDto;
import com.itacademy.akulov.entity.Course;
import com.itacademy.akulov.entity.CourseComments;
import com.itacademy.akulov.entity.CourseType;
import com.itacademy.akulov.entity.KnowlegeBase;
import com.itacademy.akulov.entity.StudentUser;
import com.itacademy.akulov.entity.TeacherUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = PersistenceConfig.class)
@Transactional
public class CourseCustomRepositoryImplTest {

    @Autowired
    private CourseRepository courseRepository;

    @Before
    public void before() {
        List<CourseComments> comments = new ArrayList<>();
        List<StudentUser> students = new ArrayList<>();
        List<TeacherUser> teachers = new ArrayList<>();
        List<KnowlegeBase> knowlegeBases = new ArrayList<>();
        Course course = courseRepository.save(Course.builder()
                .id(1L)
                .type(CourseType.FULL_TIME)
                .plan("hello")
                .name("jd1")
                .duration(70)
                .description("Helllo1")
                .startDate(LocalDate.parse("2019-07-20"))
                .build());
        course.setCourseComments(comments);
        course.setStudents(students);
        course.setTeachers(teachers);
        course.setKnowlegeBases(knowlegeBases);
    }

    @Test
    public void test1() {
        Course course = null;
        if (courseRepository.findById(1L).isPresent()) {
            course = courseRepository.findById(1L).get();
        }
        assert course != null;
        Assert.assertTrue(course.getId() > 0);
        Assert.assertEquals(course.getStartDate(), LocalDate.parse("2019-07-20"));
        Assert.assertEquals(course.getType(), CourseType.FULL_TIME);
        Assert.assertEquals(course.getName(), "jd1");
        Assert.assertEquals(course.getPlan(), "hello");
        Assert.assertEquals(course.getDescription(), "Helllo1");
        Assert.assertEquals(70, (int) course.getDuration());
        Assert.assertNotNull(course.getStudents());
        Assert.assertNotNull(course.getTeachers());
        Assert.assertNotNull(course.getCourseComments());
        Assert.assertNotNull(course.getKnowlegeBases());
    }

    @Test
    public void test2() {
        List<Course> list = new ArrayList<>();
        courseRepository.findAll().forEach(list::add);
        Assert.assertTrue(list.size() > 0);
    }

    @Test
    public void test3() {
        List<Course> list = new ArrayList<>();
        courseRepository.findAll().forEach(list::add);
        courseRepository.deleteById(list.get(0).getId());
        Assert.assertFalse(courseRepository.existsById(1L));
    }

    @Test
    public void test4() {
        List<Course> list = new ArrayList<>();
        Page<Course> page = courseRepository.getPaginationRule(PaginationDto.builder()
                .page(0)
                .pageSize(3)
                .parameter1("jd1")
                .parameter2("FULL_TIME")
                .parameter3("2019-07-17")
                .parameter4("2019-07-25")
                .build());
        System.out.println(page);
        Assert.assertNotNull(page);
        Assert.assertTrue(page.getTotalElements() > 0);
        Assert.assertTrue(page.getTotalPages() > 0);
    }


}