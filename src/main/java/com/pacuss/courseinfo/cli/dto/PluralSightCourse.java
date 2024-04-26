package com.pacuss.courseinfo.cli.dto;

public record PluralSightCourse(String id, String title, String duration,
                                String contentUrl, boolean isRetired) {

    public PluralSightCourse {
        filled(id);
        filled(title);
        filled(duration);
        filled(contentUrl);
        //notes.ifPresent(PluralSightCourse::filled);
    }

    private static void filled(String s) {
        if(s == null || s.isBlank()) {
            throw new IllegalArgumentException("No value present!");
        }
    }
}
