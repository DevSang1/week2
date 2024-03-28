package hanghae.enrolment.repository;

import hanghae.enrolment.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    long countByLectureId(long lectureId);
    boolean existsByUserIdAndLectureId(long userId, long lectureId);
    List<Enrollment> findByUserId(Long userId);
}
