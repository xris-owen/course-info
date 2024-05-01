package com.pacuss.courseinfo.domain;

import java.util.Optional;

public record Course(String id, String title, long duration, String url, Optional<String> notes) {

    // Compact constructor where you don't need to list all the constructor parameters
    public Course{
        filled(id);
        filled(title);
        filled(url);
        notes.ifPresent(Course::filled);
    }

    // Helper function used for validation
    private static void filled(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException("No value present!");
        }
    }
}
