package com.paypal.bfs.test.bookingserv.api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;



import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "booking")
@Data
public class Booking {

    @Id
    @Column(name = "booking_id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date", nullable = false)
    private Date birthDate;

    @Column(name = "check_in_date_time", nullable = false)
    private Instant checkInDate;

    @Column(name = "check_out_date_time", nullable = false)
    private Instant checkOutDate;

    @Column(name = "total_price", nullable = false)
    private Double totalPrice;

    @Column(name = "deposit", nullable = false)
    private Double deposit;

    @OneToOne(mappedBy = "booking", cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Address address;

    @Override
    public String toString() {
        return "Booking{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", deposit=" + deposit +
                ", address=" + address +
                '}';
    }
}
