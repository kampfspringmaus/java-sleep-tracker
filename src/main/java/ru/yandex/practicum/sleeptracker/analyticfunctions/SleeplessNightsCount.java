package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class SleeplessNightsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        //Определяем, какую ночь считать бессонной
        Predicate<List<SleepingSession>> whichNightToAccount = (sleepingList) -> {
                return sleepingList.get(0).getStart().toLocalTime().isBefore(LocalTime.of(12,0));
            };
        boolean isCurrentNight = whichNightToAccount.test(sessions);
        int totalNights = Period.between(sessions.getFirst().getStart().toLocalDate(),sessions.getLast().getFinish().toLocalDate()).getDays();
        if (!isCurrentNight) {
            totalNights = totalNights - 1;
        }
        System.out.println(totalNights);

        int nightSleepSessions = (int) sessions.stream()
                .filter(session ->
                        (session.getStart().toLocalDate().isBefore(session.getFinish().toLocalDate())) ||
                                (session.getStart().toLocalTime().isBefore(LocalTime.of(6,0))))
                //  .filter(session -> session.getStart().toLocalTime().isBefore(LocalTime.of(6,0)))
                .count();
        return new SleepAnalysisResult(totalNights-nightSleepSessions, "Количество бессонных ночей: ");

    }



}


