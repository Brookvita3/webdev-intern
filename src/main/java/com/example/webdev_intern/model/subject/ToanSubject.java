package com.example.webdev_intern.model.subject;

import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import com.example.webdev_intern.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToanSubject implements SubjectReport {

    private final StudentRepository studentRepository;

    @Override
    public String getName() {
        return "toan";
    }

    @Override
    public SubjectScoreLevelProjection getReport() {
        return studentRepository.getToanReport();
    }
}
