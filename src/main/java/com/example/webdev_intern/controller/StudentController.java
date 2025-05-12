package com.example.webdev_intern.controller;

import com.example.webdev_intern.response.ResponseObject;
import com.example.webdev_intern.service.ScoreReportService;
import com.example.webdev_intern.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;
    private final ScoreReportService scoreReportService;

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

        List<Map<String, Object>> data;
        if (subject == null) {
            List<String> allSubjects = List.of(
                    "toan", "ngu_van", "ngoai_ngu", "vat_li", "hoa_hoc",
                    "sinh_hoc", "lich_su", "dia_li", "gdcd"
            );

            data = allSubjects.stream()
                    .map(scoreReportService::generateFeatureReport)
                    .flatMap(List::stream)
                    .toList();
        } else {
            data = scoreReportService.generateFeatureReport(subject);
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                ResponseObject.builder()
                        .status(HttpStatus.OK.value())
                        .message("Get report successfully")
                        .data(data)
                        .build()
        );
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
