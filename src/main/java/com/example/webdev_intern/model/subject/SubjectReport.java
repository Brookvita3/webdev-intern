package com.example.webdev_intern.model.subject;

import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;

public interface SubjectReport {
    String getName();
    SubjectScoreLevelProjection getReport();
}
