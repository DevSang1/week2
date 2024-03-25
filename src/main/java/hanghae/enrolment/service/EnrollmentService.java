package hanghae.enrolment.service;

import hanghae.enrolment.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public void enrollment(Long userId, Long lectureId) {
        
    }
}
