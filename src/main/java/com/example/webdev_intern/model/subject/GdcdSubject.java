package com.example.webdev_intern.model.subject;

import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import com.example.webdev_intern.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GdcdSubject implements SubjectReport {

    private final StudentRepository studentRepository;

    @Override
    public String getName() {
        return "gdcd";
    }
    @Override
    public SubjectScoreLevelProjection getReport() {
        return studentRepository.getGdcdReport();
    }
}
