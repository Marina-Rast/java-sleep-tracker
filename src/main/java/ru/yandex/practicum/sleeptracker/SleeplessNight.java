package ru.yandex.practicum.sleeptracker;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleeplessNight implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions == null || sessions.isEmpty()) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        LocalDate firstDate = sessions.get(0).getStart().toLocalDate();
        LocalDate lastDate = sessions.get(sessions.size() - 1).getFinish().toLocalDate();

        if (sessions.get(0).getStart().getHour() >= 12) {
            firstDate = firstDate.plusDays(1);
        }

        if (sessions.get(sessions.size() - 1).getFinish().getHour() < 6) {
            lastDate = lastDate.minusDays(1);
        }

        long limitDays = ChronoUnit.DAYS.between(firstDate, lastDate) + 1;
        if (limitDays <= 0) {
            return new SleepAnalysisResult("Количество бессонных ночей", 0);
        }

        long sleeplessNights = Stream.iterate(firstDate, date -> date.plusDays(1))
                .limit(limitDays)
                .filter(date -> sessions.stream().anyMatch(session ->
                        session.getStart().toLocalDate().equals(date) ||
                                session.getFinish().toLocalDate().equals(date)
                ))
                .filter(date -> {
                    LocalDateTime nightStart = date.atTime(0, 0);
                    LocalDateTime nightEnd = date.atTime(6, 0);

                    boolean hasNightSleep = sessions.stream()
                            .anyMatch(session ->
                                    session.getStart().isBefore(nightEnd) &&
                                            session.getFinish().isAfter(nightStart)
                            );

                    return !hasNightSleep;
                })
                .count();

        return new SleepAnalysisResult("Количество бессонных ночей", (int) sleeplessNights);
    }
}