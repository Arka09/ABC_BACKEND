package com.example.demo.service;

import com.example.demo.models.BookingDetails;
import com.example.demo.models.ClassDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudioService {

    private final List<ClassDetails> classList = new ArrayList<>();
    private final List<BookingDetails> bookingList = new ArrayList<>();

    public List<BookingDetails> bookClassService(BookingDetails bookingDetails) {
        for (ClassDetails classDetails : classList) {
            if (!bookingDetails.getDate().isBefore(classDetails.getStartDate()) &&
                    !bookingDetails.getDate().isAfter(classDetails.getEndDate())) {
                int currentBookings = classDetails.getBookingsPerDay().get(bookingDetails.getDate());

                classDetails.getBookingsPerDay().put(bookingDetails.getDate(), currentBookings + 1);
                bookingList.add(bookingDetails);
                return bookingList;
            }
        }
        return new ArrayList<>();
    }

    public boolean createClassService(ClassDetails classDetails) {
        for (ClassDetails existingClass : classList) {
            if (!classDetails.getStartDate().isAfter(existingClass.getEndDate()) &&
                    !classDetails.getEndDate().isBefore(existingClass.getStartDate())) {
                return false;
            }
        }
        long daysBetween = ChronoUnit.DAYS.between(classDetails.getStartDate(), classDetails.getEndDate()) + 1;
        classDetails.setTotalClasses((int) daysBetween);

        LocalDate currentDate = classDetails.getStartDate();
        while (!currentDate.isAfter(classDetails.getEndDate())) {
            classDetails.getBookingsPerDay().put(currentDate, 0);
            currentDate = currentDate.plusDays(1);
        }
        classList.add(classDetails);
        return true;
    }

    public List<BookingDetails> getAllBookingsService() {
        return bookingList;
    }
}
