package com.example.demo.controller;

import com.example.demo.models.BookingDetails;
import com.example.demo.models.ClassDetails;
import com.example.demo.service.StudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudioController {

    StudioService studioService;
    @Autowired
    public StudioController(StudioService studioService) {
        this.studioService = studioService;
    }

    @PostMapping("/classes")
    public ResponseEntity<String> createClass(@RequestBody ClassDetails classDetails) {
        if(!studioService.createClassService(classDetails)) {
            return new ResponseEntity<>("Class already exists on these dates", HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<>("Class created successfully", HttpStatus.CREATED);
    }

    @PostMapping("/bookings")
    public ResponseEntity<String> bookClass(@RequestBody BookingDetails bookingDetails) {

        if (!studioService.bookClassService(bookingDetails).isEmpty()) {
            return new ResponseEntity<>("Booking successful", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("No class available on the selected date", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("get-all-bookings")
    public ResponseEntity<List<BookingDetails>> getAllBookings() {
        return new ResponseEntity<>(studioService.getAllBookingsService(), HttpStatus.OK);

    }
}

