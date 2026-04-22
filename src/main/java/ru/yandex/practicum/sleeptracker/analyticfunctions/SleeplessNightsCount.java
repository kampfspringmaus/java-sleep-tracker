package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.nio.file.attribute.UserPrincipalLookupService;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        int nightSleepSessions = (int) sessions.stream()
                .filter(session ->
                        (session.getStart().toLocalDate().isBefore(session.getFinish().toLocalDate())) ||
                                (session.getStart().toLocalTime().isBefore(LocalTime.of(6,0))))
              //  .filter(session -> session.getStart().toLocalTime().isBefore(LocalTime.of(6,0)))
                .count();
        int totalNights;
        if (sessions.getFirst().getStart().toLocalTime().isBefore(LocalTime.of(12,0))) {
            totalNights = Period.between(sessions.getFirst().getStart().toLocalDate(),sessions.getLast().getFinish().toLocalDate()).plusDays(1).getDays();
        } else {
            totalNights = Period.between(sessions.getFirst().getStart().toLocalDate().plusDays(1),sessions.getLast().getFinish().toLocalDate()).plusDays(1).getDays();
        }

        return new SleepAnalysisResult(totalNights-nightSleepSessions, "Количество бессонных ночей: ");

    }
}
