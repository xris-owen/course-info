package com.pacuss.courseinfo.server;

import com.pacuss.courseinfo.repository.CourseRepository;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.bridge.SLF4JBridgeHandler;

import javax.print.DocFlavor;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Properties;
import java.util.logging.LogManager;

public class CourseServer {

    // Static initializer
    static {
        // Reset the JDK logging LogManager.
        LogManager.getLogManager().reset();
        // Install necessary hooks needed to redirect JDK logging to SLF4J.
        SLF4JBridgeHandler.install();
    }
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    public static void main(String[] args) {
        String dbFilename = loadDbFilename("course-info.db");
        String baseUrl = loadDbFilename("course-info.server-base-url");
        LOG.info("Starting HTTP Server with database {}", dbFilename);
        CourseRepository courseRepository = CourseRepository.openCourseRepository(dbFilename);
        ResourceConfig config = new ResourceConfig().register(new CourseResource(courseRepository));

        GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUrl), config);
    }

    private static String loadDbFilename(String property) {
        try(InputStream propertiesStream = CourseServer.class.getResourceAsStream("/server.properties")){
            Properties properties = new Properties();
            properties.load(propertiesStream);
            return properties.getProperty(property);
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
