package com.example.webdev_intern.service.report;

import com.example.webdev_intern.model.subject.SubjectReport;
import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SubjectManager subjectManager;

    public SubjectScoreLevelProjection generateFeatureReport(String subject) {
        return subjectManager.getSubjectReport(subject);
    }

    public Map<String, SubjectReport> getFullReport() {
        return subjectManager.getFullReport();
    }

}
