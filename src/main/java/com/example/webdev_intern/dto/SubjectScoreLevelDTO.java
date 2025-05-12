package com.example.webdev_intern.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SubjectScoreLevelDTO {
    private Long getLevel1; // >=8
    private Long getLevel2; // 6-7.99
    private Long getLevel3; // 4-5.99
    private Long getLevel4; // <4
}
