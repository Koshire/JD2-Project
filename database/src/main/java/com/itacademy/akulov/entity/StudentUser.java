package com.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@DiscriminatorValue("student_user")
public class StudentUser extends User {

    @OneToMany(mappedBy = "userResult", cascade = CascadeType.REMOVE)
    private List<Result> result;

    @Builder
    public StudentUser(Long id,
                       String email,
                       String password,
                       Role role,
                       UserData userData,
                       Boolean blockList,
                       List<CourseComments> courseComments,
                       List<Result> result) {
        super(id, email, password, role, userData, blockList, courseComments);
        this.result = result;
    }
}


