package hanghae.enrolment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter @Setter
public class Enrollment {
    @Id @GeneratedValue

    private Long enrollmentId;
    @JoinColumn(name = "UserId")
    private Long userId;
    @JoinColumn(name = "LectureId")
    private Long lectureId;
    private Date enrollmentDate;

}
