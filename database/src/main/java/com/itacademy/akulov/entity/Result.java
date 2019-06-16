package com.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "course_student")
public class Result {

    @EmbeddedId
    private UserCourse userCourse;

    @Column(name = "result")
    private int result;

    @MapsId("courseId")
    @JoinColumn(name = "course_id")
    @ManyToOne
    private Course course;

    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User userResult;

    @Data
    @Embeddable
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserCourse implements Serializable {

        private static final long serialVersionUID = 1L;

        @Column(name = "user_id")
        private Long userId;

        @Column(name = "course_id")
        private Long courseId;
    }
}
