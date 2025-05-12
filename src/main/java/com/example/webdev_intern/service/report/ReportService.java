package com.example.webdev_intern.service.report;

import com.example.webdev_intern.dto.SubjectReportDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final SubjectManager subjectManager;

    public SubjectReportDTO getSubjectReport(String subject) {
        return subjectManager.getSubjectReport(subject);
    }

    public List<SubjectReportDTO> getListSubjectReports() {
        return subjectManager.getListSubjectReport();
    }
}
