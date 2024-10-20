package com.example.demo.models;

import lombok.Data;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Data
public class ClassDetails {
    // To store class details
    private String className;
    private LocalDate startDate;
    private LocalDate endDate;
    private int capacity;
    private int totalClasses;

    // Map to store the number of bookings per day, where key = date and value = number of bookings
    private Map<LocalDate, Integer> bookingsPerDay = new HashMap<>();
}

