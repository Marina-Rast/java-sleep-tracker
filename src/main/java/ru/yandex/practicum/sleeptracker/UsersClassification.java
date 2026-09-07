package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class UsersClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {

    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions.isEmpty()) {
            return new SleepAnalysisResult("Тип пользователя", Chronotype.PIGEON);
        }

        Map<ru.yandex.practicum.sleeptracker.Chronotype, Long> countMap = sessions.stream()
                .filter(session ->
                        !session.getStart().toLocalDate().equals(session.getFinish().toLocalDate()))
                .map(session -> {
                    int start = session.getStart().getHour();
                    int finish = session.getFinish().getHour();

                    if (start >= 23 && finish >= 9) {
                        return Chronotype.OWL;
                    } else if (start < 22 && finish < 7) {
                        return Chronotype.LARK;
                    } else {
                        return Chronotype.PIGEON;
                    }
                })
                .collect(Collectors.groupingBy(
                        type -> type,
                        Collectors.counting()
                ));

        if (countMap.isEmpty()) {
            return new SleepAnalysisResult("Тип пользователя", Chronotype.PIGEON);
        }

        long maxCount = countMap.values().stream()
                .max(Long::compareTo)
                .orElse(0L);

        List<Chronotype> maxTypes = countMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        Chronotype resultType;
        if (maxTypes.size() > 1) {
            resultType = Chronotype.PIGEON;
        } else {
            resultType = maxTypes.get(0);
        }

        return new SleepAnalysisResult("Тип пользователя", resultType);
    }
}