package com.evenstein.rest.controller;

import com.evenstein.rest.domain.Schedule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Class {@code ScheduleController} provides mapping to retrieve schedule of Movies.
 * To get Schedule simply add {@code /schedule} to your domain address.
 *
 * @author Vitaly Sokolsky
 */
@Controller
@RequestMapping("/schedule")
public class ScheduleController {

    Schedule schedule = new Schedule();

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List schedulePage(Model model) {
        return schedule.getCinemaSchedule();
    }

}
