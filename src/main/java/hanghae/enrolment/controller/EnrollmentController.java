package hanghae.enrolment.controller;

import hanghae.enrolment.domain.Enrollment;
import hanghae.enrolment.error.AlreadyEnrolledException;
import hanghae.enrolment.error.CapacityExceededException;
import hanghae.enrolment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 특정 학생의 수강 신청 목록을 조회하는 GET 요청
    @GetMapping("/enrollments/{userId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByUser(@PathVariable Long userId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByUserId(userId);
        if (enrollments.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enrollments);
    }

    // 수강 신청 처리를 위한 POST 요청
    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@RequestBody Enrollment request) {
        try {
            Enrollment enrollment = enrollmentService.enrollInLecture(request.getUserId(), request.getLectureId());
            return ResponseEntity.ok(enrollment);
        } catch (CapacityExceededException e) {
            return ResponseEntity.badRequest().body("정원 초과: " + e.getMessage());
        } catch (AlreadyEnrolledException e) {
            return ResponseEntity.badRequest().body("중복 수강신청: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}