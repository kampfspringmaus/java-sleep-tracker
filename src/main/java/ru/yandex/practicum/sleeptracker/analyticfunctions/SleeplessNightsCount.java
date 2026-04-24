package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

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
            return sleepingList.get(0).getStart().toLocalTime().isBefore(LocalTime.of(12, 0));
        };
        /*если в логе сна первая запись начинается раньше 12 часов дня, то берём все ночи в расчёт,
        если позже 12, то первая календарная ночь не учитывается*/
        boolean isCurrentNight = whichNightToAccount.test(sessions);
        int totalNights = Period.between(sessions.getFirst().getStart().toLocalDate(), sessions.getLast().getFinish().toLocalDate()).getDays();
        System.out.println(totalNights);
        if (!isCurrentNight) {
            totalNights = totalNights - 1;
            System.out.println(totalNights);
        }
        //Смотрим количество ночей, в которые парользователь спал
        int nightSleepSessions = (int) sessions.stream()
                .filter(session ->
                        (session.getStart().toLocalDate().isBefore(session.getFinish().toLocalDate())) ||
                                (session.getStart().toLocalTime().isBefore(LocalTime.of(6, 0))))
                .count();

        return new SleepAnalysisResult(totalNights - nightSleepSessions, "Количество бессонных ночей: ");

    }


}


