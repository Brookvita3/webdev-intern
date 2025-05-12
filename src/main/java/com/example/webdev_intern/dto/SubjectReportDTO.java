package com.example.webdev_intern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectReportDTO {
    private String subject;
    private SubjectScoreLevelDTO subjectScoreLevelDTO;
}
