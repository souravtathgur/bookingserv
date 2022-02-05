package com.paypal.bfs.test.bookingserv.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paypal.bfs.test.bookingserv.api.model.Address;
import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.api.repository.BookingeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import testing.SlowTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@SlowTest
class BookingResourceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private BookingeRepository bookingeRepository;

    @Test
    @DisplayName("When all bookings are requested then they are all returned")
    void allBookingRequested() throws Exception {
        mockMvc
                .perform(get("/v1/bfs/booking"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize((int) bookingeRepository.count())));
    }

    @Test
    @DisplayName("When a Booking creation is requested with invalid request then bad request exception occures")
    void badRequestExcception() throws Exception {

        Booking booking = getBooking();
        booking.setFirstName("");
        mockMvc
                .perform(
                        post("/v1/bfs/booking")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(booking)))
                                        .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("When a Booking creation is requested then it is persisted")
    void bookingCreatedCorrectly() throws Exception {

        Booking booking = getBooking();

        Long newBookingId =
                mapper
                        .readValue(
                                mockMvc
                                        .perform(
                                                post("/v1/bfs/booking")
                                                        .contentType(MediaType.APPLICATION_JSON)
                                                        .content(mapper.writeValueAsString(booking)))
                                        .andExpect(status().isCreated())
                                        .andReturn()
                                        .getResponse()
                                        .getContentAsString(),
                                Booking.class)
                        .getId();

        booking.setId(newBookingId);

        assertThat(
                bookingeRepository
                        .findById(newBookingId)
                        .orElseThrow(
                                () -> new IllegalStateException("New Booking has not been saved in the repository")),
                equalTo(booking));
    }

    private Booking  getBooking() {
        Booking booking=new Booking();
        booking.setFirstName("Sourav");
        booking.setLastName("Tathgur");
        booking.setBirthDate(new Date());
        booking.setCheckInDate(Instant.now());
        booking.setCheckOutDate(Instant.now());
        booking.setDeposit(20.0);
        booking.setTotalPrice(11.0);
        Address address = new Address();
        booking.setAddress(address);
        address.setLine1("line1");
        address.setLine2("line2");
        address.setCity("ludhiana");
        address.setState("punjab");
        address.setZipCode("141008");
        booking.setAddress(address);
        return booking;
    }

}
