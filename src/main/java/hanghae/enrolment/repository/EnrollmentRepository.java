package hanghae.enrolment.repository;

import hanghae.enrolment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean checkUserAndLecture(Long userId, Long lectureId);
}
