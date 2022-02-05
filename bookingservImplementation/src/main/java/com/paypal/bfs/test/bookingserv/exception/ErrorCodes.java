package com.paypal.bfs.test.bookingserv.exception;

public enum ErrorCodes {
    INVALID_REQUEST(1000,"Request is Invalid"),
    FIRST_NAME_INVALID(1001,"Please enter valid first name"),
    LAST_NAME_INVALID(1002,"Please enter valid last name"),
    DOB_INVALID(1003,"Please enter valid date of birth"),
    CHECK_IN_DATE_INVALID(1004,"Please enter valid check in date"),
    CHECK_OUT_DATE_INVALID(1005,"Please enter valid check out date"),
    AMOUNT_INVALID(1006,"Please enter valid amount value"),
    DEPOSIT_AMOUNT_INVALID(1007,"Please enter valid deposit amount value"),
    ADDRESS_MISSING(1008,"Please enter address details"),
    ADDRESS_LINE1_INVALID(1009,"Please enter address line 1 details"),
    ADDRESS_CITY_INVALID(1010,"Please enter address city"),
    ADDRESS_STATE_INVALID(1011,"Please enter address state"),
    ADDRESS_ZIPCODE_INVALID(1012,"Please enter address zipcode"),
    BOOKING_ID_INVALID(1013,"Please provide booking Id");

    private int code;
    private String message;
     ErrorCodes(int code,String msg){
        this.code=code;
        this.message=msg;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}
