package com.example.webdev_intern.model.subject;

import com.example.webdev_intern.dto.SubjectScoreLevelDTO;
import com.example.webdev_intern.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NguvanSubject implements SubjectReport {

    private final StudentRepository studentRepository;

    @Override
    public String getName() {
        return "ngu_van";
    }

    @Override
    @Cacheable("subjectReports")
    public SubjectScoreLevelDTO getScoreLevelDTO() {
        return SubjectReport.toSubjectScoreLevelDTO(studentRepository.getNguVanScoreLevelProjection());
    }
}
