package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class UsersClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {

    public enum Chronotype {
        OWL,
        LARK,
        PIGEON
    }

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Тип пользователя", "голубь");
        }

        Map<String, Long> countMap = sessions.stream()
                .filter(session -> !session.getStart().toLocalDate().equals(session.getFinish().
                        toLocalDate()))
                .map(session -> {
                    int start = session.getStart().getHour();
                    int finish = session.getFinish().getHour();

                    if (start >= 23 && finish >= 9) {
                        return "сова";
                    } else if (start < 22 && finish < 7) {
                        return "жаворонок";
                    } else {
                        return "голубь";
                    }
                })
                .collect(Collectors.groupingBy(
                        type -> type,
                        Collectors.counting()
                ));

        if (countMap.isEmpty()) {
            return new SleepAnalysisResult("Тип пользователя", "голубь");
        }

        long maxCount = countMap.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

        List<String> maxTypes = countMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        String resultType;
        if (maxTypes.size() > 1) {
            resultType = "голубь";
        } else {
            resultType = maxTypes.get(0);
        }

        return new SleepAnalysisResult("Тип пользователя", resultType);
    }
}