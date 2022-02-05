package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookingResourceImpl implements BookingResource {

    @Autowired
    private BookingService bookingService;

    @Override
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings=bookingService.getAllBookings();
        return new ResponseEntity<>(bookings, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> getAllBookings(Long bookingId) {
        Booking booking=bookingService.getBookingById(bookingId);
        return new ResponseEntity<>(booking, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Booking> create(Booking bookingRequest) {
       Booking booking=bookingService.createBooking(bookingRequest);
       return new ResponseEntity<>(booking,HttpStatus.CREATED);
    }


}
