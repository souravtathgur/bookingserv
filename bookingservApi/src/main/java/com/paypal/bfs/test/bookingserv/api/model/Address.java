package com.paypal.bfs.test.bookingserv.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
public class Address  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id",length = 19, nullable = false)
    private long id;

    @OneToOne(fetch= FetchType.EAGER)
    @JoinColumn(name = "booking_id",referencedColumnName = "booking_id")
    @JsonBackReference
    private Booking booking;


    @Column(name = "line1", nullable = false)
    private String line1;

    @Column(name = "line2")
    private String line2;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", line1='" + line1 + '\'' +
                ", line2='" + line2 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
