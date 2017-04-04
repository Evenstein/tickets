package com.evenstein.rest.controller;

import com.evenstein.rest.domain.Reservation;
import com.evenstein.rest.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;


/**
 * Class {@code MainController} provides base reservation methods, such as:
 *  - getting information aboutDate, Time, Name of Movie, amount reserved Tickets by order ID;
 *  - reserve Ticket(s) to specified Movie;
 *  - delete reservation;
 *
 * @author Vitaly Sokolsky
 */
@Controller
@RequestMapping(value = "/reservation")
public class MainController {

    Reservation reserv = new Reservation();
    File inputFile = new File(
            "D:\\Dev JAVA\\Spring Projects\\SpringMVC\\src\\main\\resources\\reservations.txt");

    /**
     * Method {@code getReservationInfo} returns Information about your reservation. NOTE You
     * must to know order {@code ID}. To get Info just add {@code /reservation/{order_ID}}
     *
     * @param id
     * @return String
     */

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getReservationInfo(@PathVariable int id) {
        return ResponseEntity.ok(reserv.getOrder(id).toString());
    }

    /**
     * Method {@code putReservation} store information about reservation to file reservation.txt
     * in /resources directory. To save you need to add /reservation/{@code Movie_info}/{@code amount}.
     * {@code Movie_Info} represents by {@code String}. {@code amount}- by number.
     *
     * @param info
     * @param amount
     * @return String
     */
    @RequestMapping(value = "/{info}/{amount}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<String> putReservation(@PathVariable String info, @PathVariable int amount) {
        int id = Reservation.getId();
        reserv.setOrder(id, info, amount);
        try {
            PrintWriter pw = new PrintWriter(inputFile);
            String str = reserv.getOrder(id);
            if (!str.equals(null)) {
                pw.append("" + id + "@" + str + "@" + amount);
                pw.flush();
                pw.close();
            } else
                throw new IOException();

        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
        }
        String response = "Successfully reserved. Your reservation ID " + id;
        return ResponseEntity.ok(response);
    }

    /**
     * Method {@code deleteReservation} deletes reservations by ID.
     *
     * @param id
     * @return String
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> deleteReservation(@PathVariable int id) {
        Ticket ticket = reserv.removeEntryOrder(id);
        File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = "" + id + "@" + ticket.getTicketInfo() + "@" + ticket.getAmount();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                if (currentLine.equals(lineToRemove))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error occurred");
        }
        boolean successful = tempFile.renameTo(inputFile);
        if (successful)
            return ResponseEntity.ok("Successfully deleted");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Error deleting of reservation");
    }

}
