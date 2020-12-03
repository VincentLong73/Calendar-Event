package com.hedspi.team45.controller;

import java.util.Calendar;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hedspi.team45.domain.Event;
import com.hedspi.team45.service.impl.EventServiceImpl;

//@RestController
@Controller
public class MainController {

    @Autowired
    private EventServiceImpl eventService;

    @RequestMapping("/api")
    public String home() {
        return "index";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar start, @RequestParam("end") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar end) {
        return eventService.findBetween(start, end);
    }

    @PostMapping("/api/events/create")
    public void createEvent(@RequestBody Event event) {

        eventService.createEvent(event);;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public void moveEvent(@RequestBody long id) {
        eventService.moveEvent(id);
    }

    @PutMapping("/api/events/setColor")
    public void setColor( @RequestBody Event event) {
      eventService.setColor(event.getId(), event.getColor());
    }



}
