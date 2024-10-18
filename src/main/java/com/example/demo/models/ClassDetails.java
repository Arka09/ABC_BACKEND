package com.example.demo.models;

import lombok.Data;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class ClassDetails {
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private int totalClasses;

    private Map<LocalDate, Integer> bookingsPerDay = new HashMap<>();
}

