package com.paypal.bfs.test.bookingserv.validations;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.exception.Error;

import java.util.Optional;

public interface IValidator {
    public Optional<Error> validate(Booking booking);
}
