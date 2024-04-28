package com.pacuss.courseinfo.cli;

import com.pacuss.courseinfo.cli.dto.PluralSightCourse;
import com.pacuss.courseinfo.cli.service.CourseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.function.Predicate.not;

public class CourseRetriever {
    public static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("Course Retriever starting");

        if (args.length == 0){
            LOG.warn("Please enter the author's id as the first argument.");
            return;
        }

        // Try block to check for exceptions
        try{
            retrieveCourses(args[0]);
        }catch (Exception ex){
            LOG.error("Unknown exception occurred. " + ex.getMessage());
            //ex.printStackTrace();
        }
    }

    // Retrieve non-retired courses using the course service.
    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author with id " + authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        // Get courses for an author and filter retired courses
        List<PluralSightCourse> coursesToStore = courseRetrievalService.getCoursesFor(authorId)
                .stream()
                .filter(not (PluralSightCourse::isRetired))
                //.filter(course -> !course.isRetired())
                .toList();

        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
        //return coursesToStore;
    }
}
