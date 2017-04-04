package com.evenstein.rest.domain;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class {@code Schedule} retrieves a schedule of movies from file schedule.txt (which placed by
 * default in {@code /resources} directory)
 *
 * @author Vitaly Sokolsky
 */
public class Schedule {

    private List<String> cinemaSchedule = new ArrayList<>();

    public Schedule() {
        try {
            Scanner sc = new Scanner(new File(
                    "D:\\Dev JAVA\\Spring Projects\\SpringMVC\\src\\main\\resources\\schedule.txt"));
            while (sc.hasNext()) {
                this.cinemaSchedule.add(sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public List<String> getCinemaSchedule() {
        return cinemaSchedule;
    }

}
