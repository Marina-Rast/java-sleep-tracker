package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MinDurationPredicateTest {

    @Test
    void returnZeroWhenNoSessions() {
        List<SleepingSession> sessions = List.of();
        MinDurationPredicate function = new MinDurationPredicate();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(0, result.getValue());
    }

    @Test
    void returnMinDurationWhenSessionsExist() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:15", "02.10.25 07:30", "GOOD"),
                new SleepingSession("02.10.25 23:50", "03.10.25 06:40", "NORMAL"),
                new SleepingSession("03.10.25 14:10", "03.10.25 15:00", "NORMAL")
        );
        MinDurationPredicate function = new MinDurationPredicate();
        SleepAnalysisResult result = function.apply(sessions);
        assertEquals(50, result.getValue());
    }
}