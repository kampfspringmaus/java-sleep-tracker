package ru.yandex.practicum.sleeptracker;

public class SleepAnalysisResult {
    private Object result;
    private final String description;

    public SleepAnalysisResult( Object result, String description) {

        this.result = result;
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public String getDescription() {
        return description;
    }
}
