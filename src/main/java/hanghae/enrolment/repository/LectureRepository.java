package hanghae.enrolment.repository;

import hanghae.enrolment.domain.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findById(String lectureId);
    int countCapacity(Long lectureId);
}
