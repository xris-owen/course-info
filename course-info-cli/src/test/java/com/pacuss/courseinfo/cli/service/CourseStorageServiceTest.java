package com.pacuss.courseinfo.cli.service;

import com.pacuss.courseinfo.cli.dto.PluralSightCourse;
import com.pacuss.courseinfo.domain.Course;
import com.pacuss.courseinfo.repository.CourseRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralSightCourses() {
        CourseRepository courseRepository = new InMemoryTestRepo();
        CourseStorageService courseStorageService = new CourseStorageService(courseRepository);

        PluralSightCourse ps1 = new PluralSightCourse("1", "Title 1",
                "01:40:00.123", "/url-1", false);
        courseStorageService.storePluralSightCourses(List.of(ps1));

        Course expected = new Course("1", "Title 1", 100, "https://pluralsight.com/url-1",
                Optional.empty());
        assertEquals(List.of(expected),courseRepository.getAllCourses());
    }

    private static class InMemoryTestRepo implements CourseRepository {
        private final List<Course> courses = new ArrayList<>();
        @Override
        public void saveCourse(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }

        @Override
        public void addNotes(String id, String notes) {

        }
    }
}