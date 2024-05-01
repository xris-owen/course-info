package com.pacuss.courseinfo.repository;

import com.pacuss.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void saveCourse(Course course);

    List<Course> getAllCourses();

    void addNotes(String id, String notes);

    // Since we made CourseJDBCRepo package private for encapsulation purposes, we need a way to instantiate it
    // with the required datasource file. That is where below method comes in.
    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJDBCRepository(databaseFile);
    }
}
