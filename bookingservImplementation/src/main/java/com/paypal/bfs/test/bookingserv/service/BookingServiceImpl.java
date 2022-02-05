package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.repository.BookingeRepository;
import com.paypal.bfs.test.bookingserv.api.service.BookingService;
import com.paypal.bfs.test.bookingserv.exception.BadRequestException;
import com.paypal.bfs.test.bookingserv.exception.ElementNotFoundException;
import com.paypal.bfs.test.bookingserv.exception.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final BookingeRepository bookingeRepository;

    @Autowired
    private  ValidationService validationService;

    @Autowired
    BookingServiceImpl(BookingeRepository bookingeRepository){
        this.bookingeRepository=bookingeRepository;
    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingeRepository.findAll();
    }

    @Override
    public Booking getBookingById(Long id) {
        return bookingeRepository
                .findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Could not find Booking with ID provided"));
    }

    @Override
    public Booking createBooking(Booking booking) {
        Optional<Error> errorOptional=validationService.validate(booking);
        if (errorOptional.isPresent()) {
            Error error=errorOptional.get();
            throw new BadRequestException(error.getMessage());
        }
        Optional<Booking> oldBooking = bookingeRepository
                .findById(booking.getId());
        if(oldBooking.isPresent()){
            return oldBooking.get();
        }
        return bookingeRepository.save(booking);
    }
}
