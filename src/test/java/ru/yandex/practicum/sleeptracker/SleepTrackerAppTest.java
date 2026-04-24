package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyticfunctions.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SleepTrackerAppTest {
    private final List<SleepingSession> testSessions = new ArrayList<>(Arrays.asList(
            new SleepingSession(LocalDateTime.of(26, 1, 11, 23, 15), LocalDateTime.of(26, 1, 12, 7, 15), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.of(26, 1, 13, 0, 15), LocalDateTime.of(26, 1, 13, 6, 15), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.of(26, 1, 13, 20, 15), LocalDateTime.of(26, 1, 14, 11, 15), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.of(26, 1, 14, 19, 15), LocalDateTime.of(26, 1, 15, 8, 15), SleepingQuality.NORMAL),
            new SleepingSession(LocalDateTime.of(26, 1, 15, 21, 15), LocalDateTime.of(26, 1, 16, 7, 15), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.of(26, 1, 16, 22, 15), LocalDateTime.of(26, 1, 17, 7, 15), SleepingQuality.GOOD),
            new SleepingSession(LocalDateTime.of(26, 1, 18, 23, 10), LocalDateTime.of(26, 1, 19, 9, 15), SleepingQuality.BAD),
            new SleepingSession(LocalDateTime.of(26, 1, 19, 21, 15), LocalDateTime.of(26, 1, 20, 10, 15), SleepingQuality.NORMAL),
            new SleepingSession(LocalDateTime.of(26, 1, 21, 22, 15), LocalDateTime.of(26, 1, 22, 11, 15), SleepingQuality.NORMAL),
            new SleepingSession(LocalDateTime.of(26, 1, 22, 22, 15), LocalDateTime.of(26, 1, 23, 3, 15), SleepingQuality.BAD)
    ));
    private ArrayList<SleepingSession> sessions;

    @BeforeEach
    void initializeSleepingSessions() {
        sessions = new ArrayList<>(testSessions);
    }

    @Test
    public void isAvgSleepSessionEquals10() {
        AvgSessionDuration asd = new AvgSessionDuration();
        SleepAnalysisResult result = asd.apply(sessions);
        Assertions.assertEquals(612, (int) result.getResult());
    }

    @Test
    public void isSessionCountEquals10() {
        SessionCount sc = new SessionCount();
        SleepAnalysisResult result = sc.apply(sessions);
        Assertions.assertEquals(10, (int) result.getResult());
    }

    @Test
    public void isMaxSessionDurationsEquals900() {
        MaxSessionDuration msd = new MaxSessionDuration();
        SleepAnalysisResult result = msd.apply(sessions);
        Assertions.assertEquals(900, (int) result.getResult());
    }

    @Test
    public void isMinSessionDurationsEquals300() {
        MinSessionDuration msd = new MinSessionDuration();
        SleepAnalysisResult result = msd.apply(sessions);
        Assertions.assertEquals(300, (int) result.getResult());
    }

    @Test
    public void isBadSessionCountEquals3() {
        BadSessionsCount bsc = new BadSessionsCount();
        SleepAnalysisResult result = bsc.apply(sessions);
        Assertions.assertEquals(3, (int) result.getResult());
    }

    @Test
    public void isUserTypeEqualsOwl() {
        for (int i = 1; i < 11; i++) {
            sessions.add(new SleepingSession(LocalDateTime.of(26, 2, i, 23, 15),
                    LocalDateTime.of(26, 2, i+1, 9, 15), SleepingQuality.GOOD));

        }
        SleepUserType sut = new SleepUserType();
        SleepAnalysisResult result = sut.apply(sessions);
        Assertions.assertEquals(UserType.OWL, result.getResult());
        }

    @Test
    public void isUserTypeEqualsPigeon() {
        SleepUserType sut = new SleepUserType();
        SleepAnalysisResult result = sut.apply(sessions);
        Assertions.assertEquals(UserType.PIGEON, result.getResult());
    }

    @Test
    public void isUserTypeEqualsSkylark() {
        for (int i = 1; i < 11; i++) {
            sessions.add(new SleepingSession(LocalDateTime.of(26, 2, i, 21, 40),
                    LocalDateTime.of(26, 2, i+1, 6, 15), SleepingQuality.GOOD));

        }
        SleepUserType sut = new SleepUserType();
        SleepAnalysisResult result = sut.apply(sessions);
        Assertions.assertEquals(UserType.SKYLARK, result.getResult());
    }

    /* @Test
    public void isSleeplessNightsCountBeforeTwelveAMEquals10() {
        SleeplessNightsCount snc = new SleeplessNightsCount();
        SleepAnalysisResult result = snc.apply(sessions);
        Assertions.assertEquals(2, result.getResult());
    }

    @Test
    public void isSleeplessNightsCountAfterTwelveAMEquals10() {
        sessions.remove(0);
        SleeplessNightsCount snc = new SleeplessNightsCount();
        SleepAnalysisResult result = snc.apply(sessions);
        Assertions.assertEquals(2, result.getResult());
    }*/

}