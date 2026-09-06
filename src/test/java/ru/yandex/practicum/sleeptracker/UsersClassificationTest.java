package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsersClassificationTest {

    private final UsersClassification usersClassification = new UsersClassification();

    @Test
    void returnPigeonWhenListIsEmpty() {
        List<SleepingSession> sessions = List.of();
        SleepAnalysisResult result = usersClassification.apply(sessions);

        assertEquals(3, result.getValue());
        assertEquals("Тип пользователя", result.getResult());
        assertEquals("Тип пользователя: голубь", result.toString());
    }

    @Test
    void returnOwlWhenOwlNightsAreMost() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:30", "02.10.25 09:30", "GOOD"),
                new SleepingSession("02.10.25 23:45", "03.10.25 10:00", "GOOD"),
                new SleepingSession("03.10.25 21:00", "04.10.25 06:00", "GOOD")
        );

        SleepAnalysisResult result = usersClassification.apply(sessions);

        assertEquals(2, result.getValue());
        assertEquals("Тип пользователя: сова", result.toString());
    }

    @Test
    void returnLarkWhenLarkNightsAreMost() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 21:00", "02.10.25 06:30", "GOOD"),
                new SleepingSession("02.10.25 21:30", "03.10.25 06:00", "GOOD"),
                new SleepingSession("03.10.25 23:30", "04.10.25 09:30", "GOOD")
        );

        SleepAnalysisResult result = usersClassification.apply(sessions);

        assertEquals(1, result.getValue());
        assertEquals("Тип пользователя: жаворонок", result.toString());
    }

    @Test
    void returnPigeonWhenTieBreaker() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 23:30", "02.10.25 09:30", "GOOD"),
                new SleepingSession("02.10.25 21:00", "03.10.25 06:00", "GOOD")
        );

        SleepAnalysisResult result = usersClassification.apply(sessions);

        assertEquals(3, result.getValue());
        assertEquals("Тип пользователя: голубь", result.toString());
    }

    @Test
    void ignoreDaySessions() {
        List<SleepingSession> sessions = List.of(
                new SleepingSession("01.10.25 13:00", "01.10.25 15:00", "BAD"),
                new SleepingSession("01.10.25 23:30", "02.10.25 09:30", "GOOD")
        );

        SleepAnalysisResult result = usersClassification.apply(sessions);

        assertEquals(2, result.getValue());
        assertEquals("Тип пользователя: сова", result.toString());
    }
}