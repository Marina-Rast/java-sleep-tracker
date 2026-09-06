package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

public class CountSessions implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int count = sessions.size();
        return new SleepAnalysisResult("Общее количество сессий cна", count);
    }
}