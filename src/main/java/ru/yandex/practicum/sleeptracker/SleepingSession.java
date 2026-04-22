package ru.yandex.practicum.sleeptracker;

import java.time.LocalDateTime;

public class SleepingSession {
private LocalDateTime start;
private LocalDateTime finish;
private SleepingQuality quality;

    public SleepingSession(LocalDateTime start, LocalDateTime finish, SleepingQuality quality) {
        this.start = start;
        this.finish = finish;
        this.quality = quality;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public LocalDateTime getFinish() {
        return finish;
    }

    public SleepingQuality getQuality() {
        return quality;
    }
}
