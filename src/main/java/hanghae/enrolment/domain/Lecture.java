package hanghae.enrolment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter @Setter
public class Lecture {
    @Id @GeneratedValue
    @Column(name = "LectureId")
    private Long lectureId;
    private String lectureName;
    private Integer capacity;
    private Date lectureDate;
}