package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MinDurationPredicate implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Минимальная длительность сна", 0);
        }

        Duration min = sessions.stream()
                .map(SleepingSession::getDuration)
                .min(Duration::compareTo)
                .orElse(Duration.ZERO);

        long minutes = min.toMinutes();
        return new SleepAnalysisResult("Минимальная длительность сна (минуты)", (int) minutes);
    }
}