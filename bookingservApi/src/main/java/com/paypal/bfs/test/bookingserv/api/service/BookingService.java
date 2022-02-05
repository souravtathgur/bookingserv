package com.paypal.bfs.test.bookingserv.api.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    Booking getBookingById(Long id);
    Booking createBooking(Booking booking);
}
