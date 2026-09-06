package ru.yandex.practicum.sleeptracker;

import java.util.Objects;

public class SleepAnalysisResult {
    private String result;
    private int value;

    public SleepAnalysisResult(String result, int value) {
        this.result = result;
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if ("Тип пользователя".equals(result)) {
            String typeName = "голубь";
            if (value == 1) {
                typeName = "жаворонок";
            } else if (value == 2) {
                typeName = "сова";
            }
            return result + ": " + typeName;
        }
        return result + ": " + value;
    }
}
