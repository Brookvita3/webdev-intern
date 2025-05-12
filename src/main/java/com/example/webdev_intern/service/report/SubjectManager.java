package com.example.webdev_intern.service.report;

import com.example.webdev_intern.dto.SubjectReportDTO;
import com.example.webdev_intern.model.subject.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SubjectManager {
    private final Map<String, SubjectReport> subjectReports;
    private final List<SubjectReport> cachedFullSubjectReports;

    public SubjectManager(
            ToanSubject toan,
            NguvanSubject nguVan,
            NgoainguSubject ngoaiNgu,
            VatliSubject vatLi,
            HoahocSubject hoaHoc,
            SinhhocSubject sinhHoc,
            LichsuSubject lichSu,
            DialiSubject diaLi,
            GdcdSubject gdcd
    ) {
        this.subjectReports = new HashMap<>();
        this.cachedFullSubjectReports = new ArrayList<>();
        subjectReports.put(toan.getName(), toan);
        cachedFullSubjectReports.add(toan);
        subjectReports.put(nguVan.getName(), nguVan);
        cachedFullSubjectReports.add(nguVan);
        subjectReports.put(ngoaiNgu.getName(), ngoaiNgu);
        cachedFullSubjectReports.add(ngoaiNgu);
        subjectReports.put(vatLi.getName(), vatLi);
        cachedFullSubjectReports.add(vatLi);
        subjectReports.put(hoaHoc.getName(), hoaHoc);
        cachedFullSubjectReports.add(hoaHoc);
        subjectReports.put(sinhHoc.getName(), sinhHoc);
        cachedFullSubjectReports.add(sinhHoc);
        subjectReports.put(lichSu.getName(), lichSu);
        cachedFullSubjectReports.add(lichSu);
        subjectReports.put(diaLi.getName(), diaLi);
        cachedFullSubjectReports.add(diaLi);
        subjectReports.put(gdcd.getName(), gdcd);
        cachedFullSubjectReports.add(gdcd);
    }

    public SubjectReportDTO getSubjectReport(String name) {
        SubjectReport subjectReport = subjectReports.get(name);
        return new SubjectReportDTO(name, subjectReport.getScoreLevelDTO());
    }

    public List<SubjectReportDTO> getListSubjectReport() {
        return this.cachedFullSubjectReports.stream()
                .map(report -> new SubjectReportDTO(
                        report.getName(),
                        report.getScoreLevelDTO()
                ))
                .toList();
    }
}

