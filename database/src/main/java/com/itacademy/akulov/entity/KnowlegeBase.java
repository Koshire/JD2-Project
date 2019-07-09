package com.itacademy.akulov.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "knowlege_base")
public class KnowlegeBase implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userKnowlegeBase;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "text")
    private String text;

    @Builder
    public KnowlegeBase(Long id, Course course, User userKnowlegeBase, LocalDate localDate, String text) {
        this.id = id;
        this.course = course;
        this.userKnowlegeBase = userKnowlegeBase;
        this.localDate = localDate;
        this.text = text;
    }
}
