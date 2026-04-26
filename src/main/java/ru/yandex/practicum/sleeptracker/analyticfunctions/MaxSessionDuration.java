package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

public class MaxSessionDuration implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int count = sessions.stream()
                .mapToInt(session -> (int) Duration.between(session.getStart(), session.getFinish()).toMinutes())
                .max()
                .orElse(0);
        //Object obj = count;

        return new SleepAnalysisResult(count, "Самая долгая сессия сна: ");
    }
}

