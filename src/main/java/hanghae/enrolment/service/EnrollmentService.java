package hanghae.enrolment.service;

import hanghae.enrolment.domain.Enrollment;
import hanghae.enrolment.domain.Lecture;
import hanghae.enrolment.error.AlreadyEnrolledException;
import hanghae.enrolment.error.CapacityExceededException;
import hanghae.enrolment.repository.EnrollmentRepository;
import hanghae.enrolment.repository.LectureRepository;
import hanghae.enrolment.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
public class EnrollmentService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Transactional
    public Enrollment enrollInLecture(Long userId, Long lectureId) throws CapacityExceededException, AlreadyEnrolledException {
        // 유저와 강의 존재 여부 확인
        if (!userRepository.existsById(userId) || !lectureRepository.existsById(lectureId)) {
            throw new IllegalArgumentException();
        }

        // 정원 확인
        long enrolledCount = enrollmentRepository.countByLectureId(lectureId);
        Lecture lecture = lectureRepository.findById(lectureId)
                .orElseThrow(() -> new NoSuchElementException("강의를 찾을 수 없습니다."));
        if (enrolledCount >= lecture.getCapacity()) {
            throw new CapacityExceededException("정원이 가득 찼습니다.");
        }

        // 이미 수강 신청한 경우 확인
        if (enrollmentRepository.existsByUserIdAndLectureId(userId, lectureId)) {
            throw new AlreadyEnrolledException("이미 수강신청한 강의입니다.");
        }

        // 수강 신청 로직
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setLectureId(lectureId);
        enrollment.setEnrollmentDate(new Date());
        enrollmentRepository.save(enrollment);

        return enrollment;
    }

    public List<Enrollment> getEnrollmentsByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }
}