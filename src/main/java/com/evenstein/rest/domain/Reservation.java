package com.evenstein.rest.domain;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/**
 * Class {@code Reservation} provides access to reservation {@code Tickets}, such as retrieving
 * information about reservations, which placed in directory {@code /resources} in file reservations.txt,
 * and removing information from this file.
 *
 * @author Vitaly Sokolsky
 */
public class Reservation {

    private static int id = 0;
    private Map<Integer, Ticket> order = new HashMap<>();

    public Reservation() {
        try {
            Scanner sc = new Scanner(new File(
                    "D:\\Dev JAVA\\Spring Projects\\SpringMVC\\src\\main\\resources\\reservations.txt"));
            while (sc.hasNext()) {
                String str = sc.nextLine();
                if (str.equals(""))
                    continue;
                String[] arr = str.split("@");
                int id = Integer.parseInt(arr[0]);
                String info = arr[1];
                int amount = Integer.parseInt(arr[2]);
                order.put(id, new Ticket(info, amount));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getId() {
        id++;
        return id;
    }

    public Ticket removeEntryOrder(int id) {
        Ticket ticket = null;
        Iterator<Map.Entry<Integer,Ticket>> iter = order.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer,Ticket> entry = iter.next();
            if (id == entry.getKey()){
                ticket = entry.getValue();
                iter.remove();
            }
        }
        return ticket;
    }

    public String getOrder(int id) {

        if (order.containsKey(id))
            return order.get(id).toString();
        else
            return null;
    }

    public void setOrder(int id, String name, int amount) {
        order.put(id, new Ticket(name,amount));
    }

}
