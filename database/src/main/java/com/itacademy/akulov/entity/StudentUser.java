package com.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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

    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Result> result;

    @Builder
    public StudentUser(Long id, String email, String password, Role role, UserData userData, Boolean blockList) {
        super(id, email, password, role, userData, blockList);
    }
}

