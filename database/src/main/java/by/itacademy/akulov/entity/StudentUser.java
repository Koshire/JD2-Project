package by.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("student_user")
public class StudentUser extends User {

    /*@Column(name = "result")
    private int result;*/

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "course_student",
            joinColumns = @JoinColumn(name = "user_id"))
    @MapKeyColumn(name = "course_id")
    @Column(name = "result")
    private Map<Course, Integer> coursesResult = new HashMap<>();

    @Builder
    public StudentUser(Long id, String email, String password, Role role, UserData userData) {
        super(id, email, password, role, userData);
    }
}

