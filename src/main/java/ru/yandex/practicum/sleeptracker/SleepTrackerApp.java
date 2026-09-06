package ru.yandex.practicum.sleeptracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class SleepTrackerApp {
    public static void main(String[] args) {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        SleepTrackerApp.class.getClassLoader().getResourceAsStream
                                ("sleep_log.txt")))) {

            List<SleepingSession> sessions = reader.lines()
                    .map(line -> line.split(";"))
                    .map(parts -> new SleepingSession(parts[0], parts[1], parts[2]))
                    .collect(Collectors.toList());

            CountSessions countSessions = new CountSessions();
            SleepAnalysisResult res1 = countSessions.apply(sessions);
            System.out.println(res1.toString());

            MinDurationSession minDuration = new MinDurationSession();
            SleepAnalysisResult res2 = minDuration.apply(sessions);
            System.out.println(res2.toString());

            MaxDurationSessions maxDuration = new MaxDurationSessions();
            SleepAnalysisResult res3 = maxDuration.apply(sessions);
            System.out.println(res3.toString());

            AverageDurationSessions averageDurationSessions = new AverageDurationSessions();
            SleepAnalysisResult res4 = averageDurationSessions.apply(sessions);
            System.out.println(res4.toString());

            CountBadQualitySessions countBadQualitySessions = new CountBadQualitySessions();
            SleepAnalysisResult res5 = countBadQualitySessions.apply(sessions);
            System.out.println(res5.toString());

            SleeplessNight sleeplessNight = new SleeplessNight();
            SleepAnalysisResult res6 = sleeplessNight.apply(sessions);
            System.out.println(res6.toString());

            UsersClassification usersClassification = new UsersClassification();
            SleepAnalysisResult res7 = usersClassification.apply(sessions);
            System.out.println(res7.toString());
        } catch (IOException e) {
            System.out.println("Файл не найден " + e.getMessage());
        }
    }
}