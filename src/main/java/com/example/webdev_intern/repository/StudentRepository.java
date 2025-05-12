package com.example.webdev_intern.repository;

import com.example.webdev_intern.model.entity.GroupAStudentProjection;
import com.example.webdev_intern.model.entity.Student;
import com.example.webdev_intern.model.entity.SubjectScoreLevelProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findBySbd(@NonNull String sbd);

    @Query(value = """
        SELECT 'toan' AS subject,
               SUM(CASE WHEN toan >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN toan >= 6 AND toan < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN toan >= 4 AND toan < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN toan < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getToanScoreLevelProjection();

    @Query(value = """
        SELECT 'ngu_van' AS subject,
               SUM(CASE WHEN ngu_van >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN ngu_van >= 6 AND toan < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN ngu_van >= 4 AND toan < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN ngu_van < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getNguVanScoreLevelProjection();

    @Query(value = """
        SELECT 'ngoai_ngu' AS subject,
               SUM(CASE WHEN ngoai_ngu >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN ngoai_ngu >= 6 AND ngoai_ngu < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN ngoai_ngu >= 4 AND ngoai_ngu < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN ngoai_ngu < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getNgoaiNguScoreLevelProjection();

    @Query(value = """
        SELECT 'vat_li' AS subject,
               SUM(CASE WHEN vat_li >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN vat_li >= 6 AND vat_li < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN vat_li >= 4 AND vat_li < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN vat_li < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getVatLiScoreLevelProjection();

    @Query(value = """
        SELECT 'hoa_hoc' AS subject,
               SUM(CASE WHEN hoa_hoc >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN hoa_hoc >= 6 AND hoa_hoc < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN hoa_hoc >= 4 AND hoa_hoc < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN hoa_hoc < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getHoaHocScoreLevelProjection();

    @Query(value = """
        SELECT 'sinh_hoc' AS subject,
               SUM(CASE WHEN sinh_hoc >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN sinh_hoc >= 6 AND sinh_hoc < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN sinh_hoc >= 4 AND sinh_hoc < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN sinh_hoc < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getSinhHocScoreLevelProjection();

    @Query(value = """
        SELECT 'lich_su' AS subject,
               SUM(CASE WHEN lich_su >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN lich_su >= 6 AND lich_su < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN lich_su >= 4 AND lich_su < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN lich_su < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getLichSuScoreLevelProjection();

    @Query(value = """
        SELECT 'dia_li' AS subject,
               SUM(CASE WHEN dia_li >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN dia_li >= 6 AND dia_li < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN dia_li >= 4 AND dia_li < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN dia_li < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getDiaLiScoreLevelProjection();

    @Query(value = """
        SELECT 'gdcd' AS subject,
               SUM(CASE WHEN gdcd >= 8 THEN 1 ELSE 0 END) AS level1,
               SUM(CASE WHEN gdcd >= 6 AND gdcd < 8 THEN 1 ELSE 0 END) AS level2,
               SUM(CASE WHEN gdcd >= 4 AND gdcd < 6 THEN 1 ELSE 0 END) AS level3,
               SUM(CASE WHEN gdcd < 4 THEN 1 ELSE 0 END) AS level4
        FROM students
        """, nativeQuery = true)
    SubjectScoreLevelProjection getGdcdScoreLevelProjection();

    @Query(value = """
    SELECT sbd, toan, vat_li, hoa_hoc,
           (toan + vat_li + hoa_hoc) AS totalScore
    FROM students
    ORDER BY totalScore DESC
    LIMIT 10
    """, nativeQuery = true)
    List<GroupAStudentProjection> findTop10GroupAStudents();

}
