package com.example.webdev_intern.service;

import com.example.webdev_intern.exception.DataNotFoundException;
import com.example.webdev_intern.model.entity.Student;
import com.example.webdev_intern.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudent(String registration) {
        return studentRepository.findBySbd(registration)
                .orElseThrow(() -> new DataNotFoundException("Student not found", HttpStatus.NOT_FOUND));
    }
}
