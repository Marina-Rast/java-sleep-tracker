package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxDurationSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Максимальная длительность сна", 0);
        }

        Duration max = sessions.stream()
                .map(SleepingSession::getDuration)
                .max(Duration::compareTo)
                .orElse(Duration.ZERO);

        long minutes = max.toMinutes();
        return new SleepAnalysisResult("Максимальная длительность сна (минуты)", (int) minutes);
    }
}