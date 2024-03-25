package hanghae.enrolment.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class User {
    @Id @GeneratedValue
    @Column(name = "UserId")
    private Long userId;
    private String name;
}
