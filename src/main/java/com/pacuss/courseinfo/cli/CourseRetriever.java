package com.pacuss.courseinfo.cli;

import com.pacuss.courseinfo.cli.service.CourseRetrievalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CourseRetriever {
    public static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);
    public static void main(String[] args) {
        LOG.info("Course Retriever starting");

        if (args.length == 0){
            LOG.warn("Please enter the author's id as the first argument.");
            return;
        }

        try{
            retrieveCourses(args[0]);
        }catch (Exception ex){
            LOG.error("Unknown exception occurred. " + ex.getMessage());
            //ex.printStackTrace();
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author with id " + authorId);
        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();

        String coursesToStore = courseRetrievalService.getCoursesFor(authorId);
        LOG.info("Retrieved the following courses " + coursesToStore);
        //return coursesToStore;
    }
}
