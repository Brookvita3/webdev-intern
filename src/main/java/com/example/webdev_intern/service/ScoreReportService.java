package com.example.webdev_intern.service;

import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import com.example.webdev_intern.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScoreReportService {

    private final StudentRepository studentRepository;
    private final Map<String, SubjectScoreLevelProjection> reportSuppliers;

    public ScoreReportService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.reportSuppliers = Map.of(
                "toan", studentRepository.getToanReport(),
                "ngu_van", studentRepository.getNguVanReport(),
                "ngoai_ngu", studentRepository.getNgoaiNguReport(),
                "vat_li", studentRepository.getVatLiReport(),
                "hoa_hoc", studentRepository.getHoaHocReport(),
                "sinh_hoc", studentRepository.getSinhHocReport(),
                "lich_su", studentRepository.getLichSuReport(),
                "dia_li", studentRepository.getDiaLiReport(),
                "gdcd", studentRepository.getGdcdReport()
        );
    }

    public List<Map<String, Object>> generateFeatureReport(String subject) {
        List<Map<String, Object>> result = new ArrayList<>();

        SubjectScoreLevelProjection supplier = reportSuppliers.get(subject);

        if (supplier != null) {
            result.add(toMap(supplier));
        }
        return result;
    }

    private Map<String, Object> toMap(SubjectScoreLevelProjection p) {
        Map<String, Object> map = new HashMap<>();
        map.put("subject", p.getSubject());
        map.put(">=8", p.getLevel1());
        map.put("6-7.99", p.getLevel2());
        map.put("4-5.99", p.getLevel3());
        map.put("<4", p.getLevel4());
        return map;
    }

}
