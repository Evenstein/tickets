package com.evenstein.rest.domain;

/**
 * Class {@code Ticket} represents Ticket information, such as Date, Time, Name of Movie and
 * amount Tickets which have been reserved for this Movie.
 *
 * @author Vitaly Sokolsky
 */
public class Ticket {

    private String ticketInfo;
    private int amount;


    public Ticket(String ticketInfo, int amount) {
        this.ticketInfo = ticketInfo;
        this.amount = amount;
    }

    public String getTicketInfo() {
        return ticketInfo;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Ticket Information : " +
                "Movie date, time, name - " + ticketInfo + '\'' +
                ", amount of reserved Tickets - " + amount +
                '.';
    }
}
