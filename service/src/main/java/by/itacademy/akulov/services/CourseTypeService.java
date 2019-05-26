package by.itacademy.akulov.services;

import by.itacademy.akulov.dao.CourseTypeDao;
import by.itacademy.akulov.entity.CourseType;

import java.util.Set;

public class CourseTypeService {

    private static final CourseTypeService INSTANCE = new CourseTypeService();
    private final CourseTypeDao courseTypeDao = CourseTypeDao.getInstance();

    public CourseTypeService() {
    }

    public Set<CourseType> getAll() {
        return this.courseTypeDao.getAll();
    }

    public static CourseTypeService getInstance() {
        return INSTANCE;
    }
}
