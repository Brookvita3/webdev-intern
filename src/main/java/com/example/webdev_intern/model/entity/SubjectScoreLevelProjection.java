package com.example.webdev_intern.model.entity;

public interface SubjectScoreLevelProjection {
    Long getLevel1(); // >=8
    Long getLevel2(); // 6-7.99
    Long getLevel3(); // 4-5.99
    Long getLevel4(); // <4
}
