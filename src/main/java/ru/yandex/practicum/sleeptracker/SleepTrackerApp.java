package ru.yandex.practicum.sleeptracker;


import ru.yandex.practicum.sleeptracker.analyticfunctions.MaxSessionDuration;
import ru.yandex.practicum.sleeptracker.analyticfunctions.MinSessionDuration;
import ru.yandex.practicum.sleeptracker.analyticfunctions.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

public class SleepTrackerApp {

    public static void main(String[] args) {
        try (Stream<String> stream = Files.lines(Paths.get("C:\\Users\\p.antipov\\IdeaProjects\\sprint8\\java-sleep-tracker\\src\\main\\resources\\sleep_log.txt"))) {
            List<SleepingSession> sessions = new LinkedList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");
            ArrayList<Function<List<SleepingSession> , SleepAnalysisResult>> functions = new ArrayList<>();

            stream.forEach(line -> {
                System.out.println("Прочитана строка: " + line);
                String[] split = line.split(";");
                sessions.add(new SleepingSession(LocalDateTime.parse(split[0], formatter),
                        LocalDateTime.parse(split[1], formatter),SleepingQuality.valueOf(split[2])));

            }
        );
            functions.add(new SessionCount());
            functions.add(new MaxSessionDuration());
            functions.add(new MinSessionDuration());
            functions.add(new AvgSessionDuration());
            functions.add(new BadSessionsCount());
            functions.add(new SleeplessNightsCount());
            functions.add(new SleepUserType());
            functions.stream()
                    .forEach(function -> {
                        var funcResult = function.apply(sessions);
                        System.out.println(funcResult.getDescription() + funcResult.getResult());
                        //System.out.println(funcResult.getResult());
                    });

           /* SleepAnalysisResult s = functions.get(2).apply(sessions);
            System.out.println(s.getDescription());
            System.out.println(s.getResult());
            System.out.println(sessions.get(2).getStart().toLocalDate().isBefore(sessions.get(2).getFinish().toLocalDate()));
            System.out.println(sessions.get(2).getStart().toLocalDate());
            System.out.println(sessions.get(2).getFinish().toLocalDate());
            System.out.println(LocalTime.of(2,4));*/

        } catch (IOException e) {
            System.out.println("Проблема чтения файла" + e.getMessage());
        }


    }
}