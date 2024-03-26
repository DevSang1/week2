package hanghae.enrolment.controller;

import hanghae.enrolment.controller.EnrollmentRequest;
import hanghae.enrolment.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    // 생성자를 통한 의존성 주입
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // 수강 신청 처리를 위한 POST 요청
    @PostMapping("/enroll")
    public ResponseEntity<?> enroll(@RequestBody EnrollmentRequest request) {
        boolean result = enrollmentService.enrollInLecture(request.getUserId(), request.getLectureId());

        if (result) {
            return ResponseEntity.ok().body("Enrollment successful");
        } else {
            return ResponseEntity.badRequest().body("Enrollment failed");
        }
    }
}