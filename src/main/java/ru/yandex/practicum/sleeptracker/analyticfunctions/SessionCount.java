package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

public class SessionCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> list) {
        return new SleepAnalysisResult(list.size(), "Количество сессий сна: ");
    }


    }

