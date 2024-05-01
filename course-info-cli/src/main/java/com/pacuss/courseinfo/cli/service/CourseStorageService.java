package com.pacuss.courseinfo.cli.service;

import com.pacuss.courseinfo.cli.dto.PluralSightCourse;
import com.pacuss.courseinfo.domain.Course;
import com.pacuss.courseinfo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {

    private final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepo){
        this.courseRepository = courseRepo;
    }

    public void storePluralSightCourses(List<PluralSightCourse> pluralSightCourses){
        for (PluralSightCourse psCourse : pluralSightCourses){
            Course course = new Course(psCourse.id(), psCourse.title(), psCourse.durationInMinutes(),
                    PS_BASE_URL + psCourse.contentUrl(), Optional.empty());
            courseRepository.saveCourse(course);
        }
    }
}
