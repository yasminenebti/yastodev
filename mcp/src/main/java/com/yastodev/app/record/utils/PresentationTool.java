package com.yastodev.app.record.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PresentationTool {

    private List<Presentation> presentations = new ArrayList<>();

    public PresentationTool() {
        presentations.add(new Presentation("Java Records", "https://www.youtube.com/watch?v=QYVh2n9aXj8", 2024));
        presentations.add(new Presentation("Java 17 Features", "https://www.youtube.com/watch?v=QYVh2n9aXj8", 2021));
        presentations.add(new Presentation("Functional Programming in Java", "https://www.youtube.com/watch?v=QYVh2n9aXj8", 2023));
        presentations.add(new Presentation("Java Streams", "https://www.youtube.com/watch?v=QYVh2n9aXj8", 2022));
    }

    public List<Presentation> getPresentations() {
        return presentations;
    }
    /*public List<Presentation> getPresentationByYear(int year) {
        return presentations.stream()
                .filter(presentation -> presentation.year() === year).toList();
    }*/

    public List<Map<String, Object>> getPresentationDetails() {
        return presentations.stream()
                .map(presentation -> {
                    return Map.<String, Object>of(
                            "title", presentation.title(),
                            "url", presentation.url(),
                            "year", presentation.year()
                    );
                })
                .collect(Collectors.toList());
    }
}
