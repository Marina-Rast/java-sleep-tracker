package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SleeplessNightTest {

    private final SleeplessNight sleeplessNight = new SleeplessNight();

    @Test
    void returnZeroWhenListIsEmpty() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(0, result.getValue());
        assertEquals("Количество бессонных ночей", result.getDescription());
    }

    @Test
    void returnZeroWhenEveryNightHasSleep() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:00", "02.10.25 07:00", "GOOD"),
                new SleepingSession("02.10.25 23:30", "03.10.25 06:30", "GOOD")
        );
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(0, result.getValue());
    }

    @Test
    void returnOneWhenOneNightIsSleepless() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:00", "02.10.25 07:00", "GOOD"),
                new SleepingSession("03.10.25 07:00", "03.10.25 11:00", "BAD")
        );
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(1, result.getValue());
    }

    @Test
    void returnTwoWhenTwoNightsAreSleepless() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:00", "02.10.25 07:00", "GOOD"),
                new SleepingSession("04.10.25 07:00", "04.10.25 11:00", "BAD"),
                new SleepingSession("06.10.25 07:00", "06.10.25 11:00", "BAD")
        );
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(2, result.getValue());
    }

    @Test
    void notCountNightIfSessionEndsExactlyAtSix() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:00", "02.10.25 06:00", "GOOD")
        );
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(0, result.getValue());
    }

    @Test
    void countNightIfSessionStartsAfterSixAndEndsBeforeMidnight() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 07:00", "01.10.25 23:00", "BAD")
        );
        SleepAnalysisResult result = sleeplessNight.apply(sessions);
        assertEquals(1, result.getValue());
    }
}