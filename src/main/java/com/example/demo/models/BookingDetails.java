package com.example.demo.models;

import lombok.Data;
import java.time.LocalDate;

@Data
public class BookingDetails {
    private String name;
    private LocalDate date;
}

