package com.pacuss.courseinfo.server;

import com.pacuss.courseinfo.domain.Course;
import com.pacuss.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;


@Path("/courses")
public class CourseResource {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Course> getCourses(){
        try {
            return courseRepository.getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::duration))
                    .toList();
        } catch (RuntimeException e) {
            LOG.info("Cannot retrieve courses from the server", e);
            throw new NotFoundException(e);
        }
    }
}
