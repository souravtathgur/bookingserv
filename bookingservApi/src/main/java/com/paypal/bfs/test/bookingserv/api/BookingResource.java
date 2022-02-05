package com.paypal.bfs.test.bookingserv.api;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface BookingResource {
    /**
     * Create {@link Booking} resource
     *
     * @param booking the booking object
     * @return the created booking
     */
    @RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.POST)
    ResponseEntity<Booking> create(@RequestBody Booking booking);

    @RequestMapping(value = "/v1/bfs/booking", method = RequestMethod.GET)
    ResponseEntity<List<Booking>> getAllBookings();

    @RequestMapping(value = "/v1/bfs/booking/{bookingId}", method = RequestMethod.GET)
    ResponseEntity<Booking> getAllBookings(@PathVariable Long bookingId);
}
