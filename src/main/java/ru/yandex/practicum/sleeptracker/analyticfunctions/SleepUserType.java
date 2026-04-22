package ru.yandex.practicum.sleeptracker.analyticfunctions;

import ru.yandex.practicum.sleeptracker.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.SleepingSession;
import ru.yandex.practicum.sleeptracker.UserType;

import java.time.LocalTime;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class SleepUserType implements Function<List<SleepingSession>, SleepAnalysisResult> {
    @Override
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {

        //исключаем дневной сон
        List<SleepingSession> nightSessions = sessions.stream()
                .filter(session ->
                        !(session.getStart().toLocalTime().isAfter(LocalTime.of(9, 0))
                                && session.getStart().toLocalTime().isBefore(LocalTime.of(20, 0))
                                && session.getFinish().toLocalTime().isBefore(LocalTime.of(21, 0))


                        ))
                .collect(Collectors.toList());
        List<SleepingSession> owl = nightSessions.stream()
                .filter(session -> ((session.getStart().toLocalTime().isAfter(LocalTime.of(23, 0))
                        || session.getStart().toLocalTime().isBefore((LocalTime.of(6, 0))))
                        && session.getFinish().toLocalTime().isAfter(LocalTime.of(9, 0)))
                )
                .collect(Collectors.toList());
        List<SleepingSession> skylark = nightSessions.stream()
                        .filter(session -> (session.getStart().toLocalTime().isBefore(LocalTime.of(22,0))
                                && session.getFinish().toLocalTime().isBefore(LocalTime.of(7,0)))

                        )
                                .collect(Collectors.toList());
        UserType result;
        int owlNights = owl.size();
                int skylarkNights = skylark.size();
                        int pigeonNights = nightSessions.size() - owlNights - skylarkNights;
        if (owlNights > skylarkNights && owlNights > pigeonNights) {
            result = UserType.OWL;
        } else if (skylarkNights > owlNights && skylarkNights > pigeonNights) {
result = UserType.SKYLARK;
        } else {
            result = UserType.PIGEON;
        }

     /*   System.out.println(nightSessions.size());
        for (SleepingSession s : nightSessions) {
            System.out.println("заснул " + s.getStart() + " проснулся " + s.getFinish());
        }*/




        return new SleepAnalysisResult(result, "Хронотип этого субъекта: ");
    }
}


