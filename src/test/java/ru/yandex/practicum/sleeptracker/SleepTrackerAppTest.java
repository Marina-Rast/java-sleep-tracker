package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CountSessionsTest {

    @Test
    void returnCorrectValue() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:15", "02.10.25 07:30", "GOOD"),
                new SleepingSession("02.10.25 23:50", "03.10.25 06:40", "NORMAL")
        );
        CountSessions function = new CountSessions();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(2, result.getValue());
    }

    @Test
    void returnListIsEmpty() {
        List<SleepingSession> sessions = List.of();
        CountSessions function = new CountSessions();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(0, result.getValue());
    }
}