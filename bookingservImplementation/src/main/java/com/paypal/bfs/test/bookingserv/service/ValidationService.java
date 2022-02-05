package com.paypal.bfs.test.bookingserv.service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.exception.Error;
import com.paypal.bfs.test.bookingserv.validations.AddressValidator;
import com.paypal.bfs.test.bookingserv.validations.BookingValidator;
import com.paypal.bfs.test.bookingserv.validations.IValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidationService {

    private List<IValidator> validations;

    public ValidationService(){
        this.validations=new ArrayList<>();
        validations.add(new BookingValidator());
        validations.add(new AddressValidator());
    }

    public Optional<Error> validate(Booking booking){
        for(IValidator validator:validations){
            Optional<Error> validate = validator.validate(booking);
            if(validate.isPresent()){
                return validate;
            }
        }
        return Optional.empty() ;
    }
}
