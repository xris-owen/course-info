package com.pacuss.courseinfo.cli.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Duration;
import java.time.LocalTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record PluralSightCourse(String id, String title, String duration,
                                String contentUrl, boolean isRetired) {

    // duration: "00:05:45"
    // Code breaks when duration > "24:00:00"
    public long durationInMinutes(){
        return Duration.between(
                LocalTime.MIN,
                LocalTime.parse(duration())
        ).toMinutes();
    }
}
