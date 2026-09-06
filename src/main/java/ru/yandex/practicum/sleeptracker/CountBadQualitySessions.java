package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountBadQualitySessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long countBadSes = sessions.stream()
                .filter(s -> "BAD".equals(s.getQuality()))
                .count();
        return new SleepAnalysisResult("Количество некачественных сессий сна", (int) countBadSes);
    }
}
