package com.pacuss.courseinfo.cli.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralSightCourseTest {

    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:45, 5
            23:59:59, 1439
            00:00:00, 0
            """)
    void durationInMinutesTest(String input, long expected) {
        PluralSightCourse pluralSightCourse = new PluralSightCourse("id", "title",
                input,"www",false);
        assertEquals(expected,pluralSightCourse.durationInMinutes());
    }
}