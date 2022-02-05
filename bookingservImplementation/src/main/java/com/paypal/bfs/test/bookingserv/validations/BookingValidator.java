package com.paypal.bfs.test.bookingserv.validations;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.exception.Error;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.paypal.bfs.test.bookingserv.exception.ErrorCodes.*;

public class BookingValidator implements IValidator{

    @Override
    public Optional<Error> validate(Booking booking) {
        if(booking==null){
            return Optional.of(new Error(INVALID_REQUEST.getMessage()));
        }
        if(booking.getId()==null || booking.getId()==0){
            return Optional.of(new Error(BOOKING_ID_INVALID.getMessage()));
        }
        if(StringUtils.isEmpty(booking.getFirstName()))
            return Optional.of(new Error(FIRST_NAME_INVALID.getMessage()));
        if(StringUtils.isEmpty(booking.getLastName()))
            return Optional.of(new Error(LAST_NAME_INVALID.getMessage()));
        if(booking.getBirthDate()==null)
            return Optional.of(new Error(DOB_INVALID.getMessage()));
        if(booking.getDeposit()==null)
            return Optional.of(new Error(DEPOSIT_AMOUNT_INVALID.getMessage()));
        if(booking.getCheckInDate()==null)
            return Optional.of(new Error(CHECK_IN_DATE_INVALID.getMessage()));
        if(booking.getCheckOutDate()==null)
            return Optional.of(new Error(CHECK_OUT_DATE_INVALID.getMessage()));
        if(booking.getTotalPrice()==null)
            return Optional.of(new Error(AMOUNT_INVALID.getMessage()));
        return Optional.empty();
    }
}
