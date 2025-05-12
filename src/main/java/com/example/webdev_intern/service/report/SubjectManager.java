package com.example.webdev_intern.service.report;

import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import com.example.webdev_intern.model.subject.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SubjectManager {
    private final Map<String, SubjectReport> subjectMap;

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
        this.subjectMap = new HashMap<>();
        subjectMap.put(toan.getName(), toan);
        subjectMap.put(nguVan.getName(), nguVan);
        subjectMap.put(ngoaiNgu.getName(), ngoaiNgu);
        subjectMap.put(vatLi.getName(), vatLi);
        subjectMap.put(hoaHoc.getName(), hoaHoc);
        subjectMap.put(sinhHoc.getName(), sinhHoc);
        subjectMap.put(lichSu.getName(), lichSu);
        subjectMap.put(diaLi.getName(), diaLi);
        subjectMap.put(gdcd.getName(), gdcd);
    }

    public SubjectScoreLevelProjection getSubjectReport(String name) {
        return subjectMap.get(name).getReport();
    }

    public Map<String, SubjectReport> getFullReport() {
        return this.subjectMap;
    }


}

