package by.itacademy.akulov.dao;

import by.itacademy.akulov.entity.CourseType;
import by.itacademy.akulov.jdbc.ConnectionManager;
import by.itacademy.akulov.utils.BuildEntity;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class CourseTypeDao {

    private static final CourseTypeDao INSTANCE = new CourseTypeDao();

    private final String getAll =
            "SELECT id, name"
                    + " FROM course_type;";

    private final String getById =
            "SELECT id, name "
                    + "FROM course_type "
                    + "WHERE id = ?";

    private final String getByName =
            "SELECT id, name "
                    + "FROM course_type "
                    + "WHERE name = ?";

    private final String create =
            "INSERT INTO course_type (name)"
                    + "VALUES (?);";

    private final String del =
            "DELETE FROM course_type "
                    + "WHERE id = ?;";

    @SneakyThrows
    public Set<CourseType> getAll() {
        Set<CourseType> set = new HashSet<>();
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(getAll)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                set.add(BuildEntity.buildCourseType(resultSet));
            }
        }
        return set;
    }

    @SneakyThrows
    public Optional<CourseType> getEntityById(Integer id) {
        CourseType courseType = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(getById)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseType = BuildEntity.buildCourseType(resultSet);
            }
        }
        return Optional.ofNullable(courseType);
    }

    @SneakyThrows
    public Optional<CourseType> getEntityByName(String name) {
        CourseType courseType = null;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(getByName)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                courseType = BuildEntity.buildCourseType(resultSet);
            }
        }
        return Optional.ofNullable(courseType);
    }

    @SneakyThrows
    public boolean delete(CourseType courseType) {
        boolean result = false;
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement = connection.prepareStatement(del)) {
            preparedStatement.setInt(1, courseType.getId());
            if (preparedStatement.executeUpdate() == 1) {
                result = true;
            }
        }
        return result;
    }

    @SneakyThrows
    public CourseType save(CourseType courseType) {
        try (Connection connection = ConnectionManager.get();
             PreparedStatement preparedStatement =
                     connection.prepareStatement(create, RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, courseType.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                courseType.setId(generatedKeys.getInt(1));
            }
        }
        return courseType;
    }

    public static CourseTypeDao getInstance() {
        return INSTANCE;
    }
}
