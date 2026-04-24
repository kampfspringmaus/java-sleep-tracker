package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class AvgSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int totalSleep = sessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getStart(), session.getFinish()).toMinutes())
                .sum();
        if (sessions.size() == 0 || sessions == null) {
            return new SleepAnalysisResult(0, "Средняя продолжительность сна: ");
        }
        int result = totalSleep / sessions.size();
        return new SleepAnalysisResult(result, "Средняя продолжительность сна: ");

    }
}
