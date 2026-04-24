package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingQuality;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.List;
import java.util.function.Function;

public class BadSessionsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        int result = (int) sessions.stream()
                .filter(session -> session.getQuality() == SleepingQuality.BAD)
                .count();

        return new SleepAnalysisResult(result, "Количество плохих сессий сна: ");

    }
}
