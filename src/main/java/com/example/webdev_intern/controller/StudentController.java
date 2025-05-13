package com.example.webdev_intern.controller;

import com.example.webdev_intern.dto.StudentGroupADTO;
import com.example.webdev_intern.dto.SubjectReportDTO;
import com.example.webdev_intern.model.entity.Student;
import com.example.webdev_intern.response.ResponseObject;
import com.example.webdev_intern.service.report.ReportService;
import com.example.webdev_intern.service.student.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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


    @Operation(summary = "Search student by registration")
    @ApiResponse(
            responseCode = "200",
            description = "Successful student lookup",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Student.class)
            )
    )
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

    @Operation(summary = "Get report for all or specific subject")
    @ApiResponse(
            responseCode = "200",
            description = "Subject report(s) retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = SubjectReportDTO.class))
            )
    )
    @GetMapping("/report")
    public ResponseEntity<?> report(
            @RequestParam(required = false) String subject) {

        if (subject == null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .status(HttpStatus.OK.value())
                            .message("Get report of all subject successfully")
                            .data(reportService.getListSubjectReports())
                            .build()
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    ResponseObject.builder()
                            .status(HttpStatus.OK.value())
                            .message(String.format("Get report of %s successfully", subject))
                            .data(reportService.getSubjectReport(subject))
                            .build()
            );

        }
    }

    @Operation(summary = "Get top 10 students in Group A")
    @ApiResponse(
            responseCode = "200",
            description = "Top 10 Group A students retrieved successfully",
            content = @Content(
                    mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = StudentGroupADTO.class))
            )
    )
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
