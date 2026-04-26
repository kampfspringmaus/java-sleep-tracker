package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;

import java.time.LocalTime;
import java.time.Period;
import java.util.List;
import java.util.function.Function;

public class SleeplessNightsCount implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        if (sessions == null || sessions.size() == 0) {
            return new SleepAnalysisResult(0,"Количество бессонных ночей: ");
        }
        //Определяем, какую ночь считать бессонной
        boolean isCurrentNight = sessions.get(0).getStart().toLocalTime().isBefore(LocalTime.of(12, 0));
        /*если в логе сна первая запись начинается раньше 12 часов дня, то берём все ночи в расчёт,
        если позже 12, то первая календарная ночь не учитывается*/
        int totalNights = Period.between(sessions.getFirst().getStart().toLocalDate(), sessions.getLast().getFinish().toLocalDate()).getDays();
        if (!isCurrentNight) {
            totalNights = totalNights - 1;
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


