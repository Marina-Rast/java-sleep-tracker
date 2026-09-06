package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountBadQualitySessionsTest {

    @Test
    void returnZeroWhenNoSessions() {
        List<SleepingSession> sessions = List.of();
        CountBadQualitySessions function = new CountBadQualitySessions();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(0, result.getValue());
    }

    @Test
    void returnZeroWhenNoBadSessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:15", "02.10.25 07:30", "GOOD"),
                new SleepingSession("02.10.25 23:50", "03.10.25 06:40", "NORMAL")
        );
        CountBadQualitySessions function = new CountBadQualitySessions();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(0, result.getValue());
    }

    @Test
    void returnCorrectCountWhenBadSessionsExist() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:15", "02.10.25 07:30", "GOOD"),
                new SleepingSession("02.10.25 23:50", "03.10.25 06:40", "BAD"),
                new SleepingSession("03.10.25 14:10", "03.10.25 15:00", "NORMAL"),
                new SleepingSession("04.10.25 23:40", "05.10.25 08:00", "BAD")
        );
        CountBadQualitySessions function = new CountBadQualitySessions();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(2, result.getValue());
    }
}