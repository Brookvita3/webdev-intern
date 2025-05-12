package com.example.webdev_intern.service.student;

import com.example.webdev_intern.model.entity.Student;
import com.example.webdev_intern.repository.StudentRepository;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@RequiredArgsConstructor
public class StudentSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    public void run(String... args) throws Exception {

        log.info("Starting student seeding...");
        if (studentRepository.count() > 0) {
            log.info("Database already has data. Skipping seeding.");
            return;
        }

        String path = "dataset/diem_thi_thpt_2024.csv"; // Dùng file nhỏ để test
        try (CSVReader reader = new CSVReader(
                new InputStreamReader(new ClassPathResource(path).getInputStream()))) {

            reader.readNext(); // Bỏ qua header

            List<CompletableFuture<Void>> futures = new ArrayList<>();
            List<Student> batch = new ArrayList<>();
            int total = 0;
            long startTime = System.currentTimeMillis();


            String[] fields;
            while ((fields = reader.readNext()) != null) {
                if (fields.length < 11) continue;

                batch.add(toStudent(fields));
                total++;

                if (batch.size() == 1000) {
                    List<Student> toSave = new ArrayList<>(batch);
                    futures.add(CompletableFuture.runAsync(() -> {
                        studentRepository.saveAll(toSave);
                    }, threadPoolTaskExecutor));
                    batch.clear();
                }
            }

            if (!batch.isEmpty()) {
                List<Student> toSave = new ArrayList<>(batch);
                futures.add(CompletableFuture.runAsync(() -> {
                    studentRepository.saveAll(toSave);
                }, threadPoolTaskExecutor));
            }

            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            log.info("✅ Seeding done, total records: {}, time: {} ms", total, System.currentTimeMillis() - startTime);


        } catch (CsvValidationException e) {
            log.error("Error parsing CSV", e);
            throw e;
        } catch (Exception e) {
            log.error("Error during seeding", e);
            throw e;
        }
    }

    private Student toStudent(String[] fields) {
        return new Student(
                fields[0], parseDouble(fields[1]), parseDouble(fields[2]),
                parseDouble(fields[3]), parseDouble(fields[4]), parseDouble(fields[5]),
                parseDouble(fields[6]), parseDouble(fields[7]), parseDouble(fields[8]),
                parseDouble(fields[9]), fields[10]
        );
    }

    private Double parseDouble(String value) {
        if (value == null || value.trim().isEmpty()) return null;
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.error("Failed to parse double: {}", value, e);
            return null;
        }
    }
}
