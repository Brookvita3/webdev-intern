package com.example.webdev_intern.controller;

import com.example.webdev_intern.response.ResponseObject;
import com.example.webdev_intern.service.report.ReportService;
import com.example.webdev_intern.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ReportService reportService;

    @GetMapping("/search")
    public ResponseEntity<?> register(
            @RequestParam String registration) {

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message("Get student successfully")
                        .data(studentService.getStudent(registration))
                        .build()
        );
    }

    @GetMapping("/report")
    public ResponseEntity<?> report(
            @RequestParam(required = false) String subject) {

        if (subject == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .status(HttpStatus.OK.value())
                            .message("Get report of all subject successfully")
                            .data(reportService.getFullReport())
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .status(HttpStatus.OK.value())
                            .message(String.format("Get report of %s successfully", subject))
                            .data(reportService.generateFeatureReport(subject))
                            .build()
            );

        }
    }

    @GetMapping("/dashboard/top10/group-a")
    public ResponseEntity<?> getTop10GroupAStudents() {
        return ResponseEntity.ok(
                ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message("Top 10 students in Group A")
                        .data(studentService.getTop10GroupAStudents())
                        .build()
        );
    }
}
