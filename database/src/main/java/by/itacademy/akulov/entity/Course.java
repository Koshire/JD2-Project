package by.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "course")
public class Course implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseType type;

    @Column(name = "start_date")
    //@Temporal(TemporalType.DATE)
    private LocalDate startDate;

    @Column(name = "duration_hours")
    private Integer duration;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "plan")
    private String plan;

    @ManyToMany
    @JoinTable(name = "course_teacher",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<TeacherUser> teachers = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "course_student",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<StudentUser> students = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseComments> courseComments = new ArrayList<>();

    @Builder
    public Course(CourseType type, LocalDate startDate, Integer duration, String name, String description, String plan) {
        this.type = type;
        this.startDate = startDate;
        this.duration = duration;
        this.name = name;
        this.description = description;
        this.plan = plan;
    }
}
