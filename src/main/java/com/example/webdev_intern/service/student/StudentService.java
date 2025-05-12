package com.example.webdev_intern.service.student;

import com.example.webdev_intern.dto.StudentGroupADTO;
import com.example.webdev_intern.exception.DataNotFoundException;
import com.example.webdev_intern.model.entity.GroupAStudentProjection;
import com.example.webdev_intern.model.entity.Student;
import com.example.webdev_intern.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;

    public Student getStudent(String registration) {
        return studentRepository.findBySbd(registration)
                .orElseThrow(() -> new DataNotFoundException("Student not found", HttpStatus.NOT_FOUND));
    }

    @Cacheable("top10GroupA")
    public List<StudentGroupADTO> getTop10GroupAStudents() {
        List<GroupAStudentProjection> projectionList = studentRepository.findTop10GroupAStudents();
        return projectionList.stream()
                .map(projection ->
                        new StudentGroupADTO(
                                projection.getSbd(),
                                projection.getToan(),
                                projection.getVatLi(),
                                projection.getHoaHoc()
                        )
                )
                .toList();

    }
}
