package com.example.webdev_intern.model.subject;

import com.example.webdev_intern.dto.SubjectScoreLevelDTO;
import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;

public interface SubjectReport {
    String getName();
    SubjectScoreLevelDTO getScoreLevelDTO();
    static SubjectScoreLevelDTO toSubjectScoreLevelDTO(SubjectScoreLevelProjection s) {
        return new SubjectScoreLevelDTO(s.getLevel1(), s.getLevel2(), s.getLevel3(), s.getLevel4());
    }
}
