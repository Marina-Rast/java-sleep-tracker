package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepTrackerApp {

    private static final List<Function<List<SleepingSession>, SleepAnalysisResult>> FUNCTIONS = List.of(
            new CountSessions(),
            new MinDurationPredicate(),
            new MaxDurationPredicate(),
            new AverageDurationSessions(),
            new CountBadQualitySessions(),
            new SleeplessNight(),
            new UsersClassification()
    );

    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(SleepTrackerApp.class.getClassLoader().getResourceAsStream("sleep_log.txt"))
        )) {
            List<SleepingSession> sessions = reader.lines()
                    .map(line -> line.split(";"))
                    .map(parts -> new SleepingSession(parts[0], parts[1], parts[2]))
                    .collect(Collectors.toList());

            List<SleepAnalysisResult> results = FUNCTIONS.stream()
                    .map(func -> func.apply(sessions))
                    .collect(Collectors.toList());

            results.forEach(System.out::println);

        } catch (IOException e) {
            System.out.println("Файл не найден " + e.getMessage());
        }
    }
}