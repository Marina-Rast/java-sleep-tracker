package ru.yandex.practicum.sleeptracker;

public enum Chronotype {
    OWL("сова"),
    LARK("жаворонок"),
    PIGEON("голубь");

    private final String russName;

    Chronotype(String russName) {
        this.russName = russName;
    }

    @Override
    public String toString() {
        return russName;
    }
}