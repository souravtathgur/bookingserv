package com.paypal.bfs.test.bookingserv.validations;

import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.exception.Error;
import org.springframework.util.StringUtils;

import java.util.Optional;

import static com.paypal.bfs.test.bookingserv.exception.ErrorCodes.*;

public class AddressValidator implements IValidator{

    @Override
    public Optional<Error> validate(Booking booking) {
        Address address= booking.getAddress();
        if(address==null)
            return Optional.of(new Error(ADDRESS_MISSING.getMessage()));
        if(StringUtils.isEmpty(address.getLine1()))
            return Optional.of(new Error(ADDRESS_LINE1_INVALID.getMessage()));
        if(StringUtils.isEmpty(address.getCity()))
            return Optional.of(new Error(ADDRESS_CITY_INVALID.getMessage()));
        if(StringUtils.isEmpty(address.getState()))
            return Optional.of(new Error(ADDRESS_STATE_INVALID.getMessage()));
        if(StringUtils.isEmpty(address.getZipCode()))
            return Optional.of(new Error(ADDRESS_ZIPCODE_INVALID.getMessage()));
        return Optional.empty();
    }
}
