package hanghae.enrolment.controller;

public class EnrollmentRequest {
    private Long userId;
    private Long lectureId;

    // 기본 생성자
    public EnrollmentRequest() {}

    // Getters
    public Long getUserId() {
        return userId;
    }

    public Long getLectureId() {
        return lectureId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setLectureId(Long lectureId) {
        this.lectureId = lectureId;
    }
}