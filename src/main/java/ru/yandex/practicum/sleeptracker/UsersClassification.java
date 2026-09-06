package ru.yandex.practicum.sleeptracker;

import java.util.List;
import java.util.function.Function;

class UsersClassification implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int[] owls = {0};
        int[] larks = {0};
        int[] pigeons = {0};
        sessions.stream()
                .filter(session -> !session.getStart().toLocalDate().equals(session.getFinish()
                        .toLocalDate()))
                .forEach(session -> {
                    int start = session.getStart().getHour();
                    int finish = session.getFinish().getHour();
                    if (start >= 23 && finish >= 9) {
                        owls[0]++;
                    } else if (start < 22 && finish < 7) {
                        larks[0]++;
                    } else {
                        pigeons[0]++;
                    }
                });
        int typeUser = 3;
        if (owls[0] > larks[0] && owls[0] > pigeons[0]) {
            typeUser = 2;
        } else if (larks[0] > owls[0] && larks[0] > pigeons[0]) {
            typeUser = 1;
        }
        return new SleepAnalysisResult("Тип пользователя", typeUser);
    }
}
