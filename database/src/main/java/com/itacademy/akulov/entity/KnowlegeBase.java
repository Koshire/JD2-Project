package com.itacademy.akulov.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "knowlege_base")
public class KnowlegeBase implements BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userKnowlegeBase;

    @Column(name = "date")
    private LocalDate localDate;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "knowlegeBase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<KBComments> kbComments = new ArrayList<>();

    @Builder
    public KnowlegeBase(Long id, Course course, User userKnowlegeBase, LocalDate localDate, String text) {
        this.id = id;
        this.course = course;
        this.userKnowlegeBase = userKnowlegeBase;
        this.localDate = localDate;
        this.text = text;
    }
}
