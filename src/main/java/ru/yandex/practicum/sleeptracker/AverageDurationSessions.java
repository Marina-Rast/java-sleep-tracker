package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class AverageDurationSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long averageMinutes = Math.round(sessions.stream()
                .mapToLong(s -> s.getDuration().toMinutes())
                .average()
                .orElse(0.0));

        return new SleepAnalysisResult("Средняя продолжительность сессии (в минутах)", (int) averageMinutes);
    }
}