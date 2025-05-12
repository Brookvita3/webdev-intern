package com.example.webdev_intern.controller;

import com.example.webdev_intern.response.ResponseObject;
import com.example.webdev_intern.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.GetExchange;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    @GetExchange("/search")
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
}
