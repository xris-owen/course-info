package com.pacuss.courseinfo.cli.service;

import com.pacuss.courseinfo.cli.dto.PluralSightCourse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.URI;
import java.util.List;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CourseRetrievalService {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static final String PS_URI = "https://app.pluralsight.com/profile/data/author/%s/all-content";
    public static final HttpClient CLIENT = HttpClient
            .newBuilder()
            .followRedirects(HttpClient.Redirect.ALWAYS)
            .build();

    // Fetches course information for a given author
    public List<PluralSightCourse> getCoursesFor(String authorId){
        // Build the request with the URI
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(PS_URI.formatted(authorId)))
                .GET()
                .build();

        // Try block to check for exceptions
        try {
            HttpResponse<String> response = CLIENT.send(request, HttpResponse.BodyHandlers.ofString());
            return switch (response.statusCode()){
                case 200 -> toPluralsightCourses(response); //response.body();
                case 404 -> List.of();
                default -> throw new RuntimeException("PluralSight API call failed with status code " + response.statusCode());
            };
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Couldn't call PluralSight API at the moment.  " + e);
        }

    }

    private List<PluralSightCourse> toPluralsightCourses(HttpResponse<String> response) throws JsonProcessingException {
        JavaType returnType = OBJECT_MAPPER.getTypeFactory()
                .constructCollectionType(List.class, PluralSightCourse.class);

        // Disabling the mapper object to deserialize properties which cannot be mapped to java class
        // OR Method 2: Providing an annotation at the class level (PluralSightCourse) @JsonIgnoreProperties
        //OBJECT_MAPPER.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // mapper reading the json file and deserializes it to LIST of PluralSightCourse object
        return OBJECT_MAPPER.readValue(response.body(), returnType);
    }
}
