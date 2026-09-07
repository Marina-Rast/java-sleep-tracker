package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {

    private LocalDateTime start;
    private LocalDateTime finish;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
    private Quality quality;

    public enum Quality{
        GOOD,
        NORMAL,
        BAD;
    }
    private Duration duration;


    public SleepingSession(String start, String finish, String quality) {
        this.start = LocalDateTime.parse(start, FORMATTER);
        this.finish = LocalDateTime.parse(finish, FORMATTER);
        this.quality = Quality.valueOf(quality);
        this.duration = Duration.between(this.start, this.finish);
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public Duration getDuration() {
        return duration;
    }

    public Quality getQuality() {
        return quality;
    }
}
