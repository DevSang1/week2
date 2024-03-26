package hanghae.enrolment.service;

import hanghae.enrolment.domain.Enrollment;
import hanghae.enrolment.domain.Lecture;
import hanghae.enrolment.repository.EnrollmentRepository;
import hanghae.enrolment.repository.LectureRepository;
import hanghae.enrolment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final UserRepository userRepository;
    private final LectureRepository lectureRepository;
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    EnrollmentService(UserRepository userRepository, LectureRepository lectureRepository, LectureRepository lectureRepository1, EnrollmentRepository enrollmentRepository) {
        this.userRepository = userRepository;
        this.lectureRepository = lectureRepository1;
        this.enrollmentRepository = enrollmentRepository;
    }

    public boolean enrollInLecture(Long userId, Long lectureId) {
        // 유저 확인
        if (!userRepository.existsById(userId)) {
            // 유저가 없으면 false 반환
            return false;
        }

        // 강의 확인
        Optional<Lecture> lectureOpt = lectureRepository.findById(lectureId);
        if (!lectureOpt.isPresent()) {
            // 강의가 없으면 false 반환
            return false;
        }

        Lecture lecture = lectureOpt.get();

        // 정원 확인
        long enrolledCount = enrollmentRepository.countByLectureId(lectureId);
        if (enrolledCount >= lecture.getCapacity()) {
            // 정원 초과면 false 반환
            return false;
        }

        // 수강 신청 기록 생성 및 저장
        Enrollment enrollment = new Enrollment();
        enrollment.setUserId(userId);
        enrollment.setLectureId(lectureId);
        enrollment.setEnrollmentDate(new Date());
        enrollmentRepository.save(enrollment);

        return true;
    }
}
