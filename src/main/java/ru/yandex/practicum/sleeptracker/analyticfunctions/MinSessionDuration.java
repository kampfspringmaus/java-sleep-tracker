package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class MinSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
         int count = sessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getStart(), session.getFinish()).toMinutes())
                .min()
                .orElse(0);
        return new SleepAnalysisResult(count, "Самая короткая сессия сна: ");
    }
}
