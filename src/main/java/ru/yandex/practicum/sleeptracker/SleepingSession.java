package ru.yandex.practicum.sleeptracker;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SleepingSession {

    private LocalDateTime start;
    private LocalDateTime finish;

    private String quality;
    private Duration duration;


    public SleepingSession(String start, String finish, String quality) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
        this.start = LocalDateTime.parse(start, formatter);
        this.finish = LocalDateTime.parse(finish, formatter);
        this.quality = quality;
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

    public String getQuality() {
        return quality;
    }

}
