package com.pacuss.courseinfo.server;

import com.pacuss.courseinfo.domain.Course;
import com.pacuss.courseinfo.repository.CourseRepository;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;


@Path("/courses")
public class CourseResource {

    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);
    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses(){
        try {
            return courseRepository.getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::duration));
        } catch (RuntimeException e) {
            LOG.info("Cannot retrieve courses from the server", e);
            throw new NotFoundException(e);
        }
    }

//    @GET
//    @Produces(MediaType.APPLICATION_JSON)
//    public List<Course> getCourses(){
//        try {
//            return courseRepository.getAllCourses()
//                    .stream()
//                    .sorted(Comparator.comparing(Course::duration))
//                    .toList();
//        } catch (RuntimeException e) {
//            LOG.info("Cannot retrieve courses from the server", e);
//            throw new NotFoundException(e);
//        }
//    }
    //</editor-fold>

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id}/notes")
    public void addNotes(@PathParam("id") String id, String note){
        courseRepository.addNotes(id, note);
    }
}
