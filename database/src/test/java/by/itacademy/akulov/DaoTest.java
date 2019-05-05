package by.itacademy.akulov;

import by.itacademy.akulov.dao.CourseTypeDao;
import by.itacademy.akulov.entity.CourseType;
import org.junit.Test;

public class DaoTest {

    CourseTypeDao courseTypeDao = CourseTypeDao.getInstance();

    @Test
    public void testMethod() {
        System.out.println(courseTypeDao.getAll());
    }
}
