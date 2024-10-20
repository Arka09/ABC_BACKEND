package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.models.BookingDetails;
import com.example.demo.models.ClassDetails;
import com.example.demo.service.StudioService;

@SpringBootTest
class DemoApplicationTests {

	private StudioService studioService;

    @BeforeEach
    public void setUp() {
        studioService = new StudioService();
    }

    @Test
    public void testCreateClassService_Success() {
        ClassDetails classDetails = new ClassDetails();
        classDetails.setStartDate(LocalDate.of(2024, 10, 1));
        classDetails.setEndDate(LocalDate.of(2024, 10, 10));
        
        boolean result = studioService.createClassService(classDetails);

        assertTrue(result);
        assertEquals(10, classDetails.getTotalClasses());  // 10 days between start and end date
    }

    @Test
    public void testCreateClassService_Overlap_Failure() {
        ClassDetails classDetails1 = new ClassDetails();
        classDetails1.setStartDate(LocalDate.of(2024, 10, 1));
        classDetails1.setEndDate(LocalDate.of(2024, 10, 10));

        ClassDetails classDetails2 = new ClassDetails();
        classDetails2.setStartDate(LocalDate.of(2024, 10, 5));
        classDetails2.setEndDate(LocalDate.of(2024, 10, 15));

        studioService.createClassService(classDetails1);
        boolean result = studioService.createClassService(classDetails2);  // Should fail because of overlap

        assertFalse(result);
    }

    @Test
    public void testBookClassService_Success() {
        ClassDetails classDetails = new ClassDetails();
        classDetails.setStartDate(LocalDate.of(2024, 10, 1));
        classDetails.setEndDate(LocalDate.of(2024, 10, 10));
        studioService.createClassService(classDetails);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setDate(LocalDate.of(2024, 10, 3));
        
        List<BookingDetails> bookings = studioService.bookClassService(bookingDetails);

        assertFalse(bookings.isEmpty());
        assertEquals(1, bookings.size());
        assertEquals(1, classDetails.getBookingsPerDay().get(bookingDetails.getDate()));  // Check the booking count
    }

    @Test
    public void testBookClassService_Failure_NoClassOnDate() {
        ClassDetails classDetails = new ClassDetails();
        classDetails.setStartDate(LocalDate.of(2024, 10, 1));
        classDetails.setEndDate(LocalDate.of(2024, 10, 10));
        studioService.createClassService(classDetails);

        BookingDetails bookingDetails = new BookingDetails();
        bookingDetails.setDate(LocalDate.of(2024, 10, 15));  // No class on this date
        
        List<BookingDetails> bookings = studioService.bookClassService(bookingDetails);

        assertTrue(bookings.isEmpty());  // No bookings should be added
    }

    @Test
    public void testGetAllBookingsService() {
        ClassDetails classDetails = new ClassDetails();
        classDetails.setStartDate(LocalDate.of(2024, 10, 1));
        classDetails.setEndDate(LocalDate.of(2024, 10, 10));
        studioService.createClassService(classDetails);

        BookingDetails bookingDetails1 = new BookingDetails();
        bookingDetails1.setDate(LocalDate.of(2024, 10, 3));

        BookingDetails bookingDetails2 = new BookingDetails();
        bookingDetails2.setDate(LocalDate.of(2024, 10, 4));

        studioService.bookClassService(bookingDetails1);
        studioService.bookClassService(bookingDetails2);

        List<BookingDetails> allBookings = studioService.getAllBookingsService();

        assertEquals(2, allBookings.size());
    }

}

