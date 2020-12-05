package com.hedspi.team45.controller;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.hedspi.team45.entity.Event;
import com.hedspi.team45.service.impl.EventServiceImpl;

@RestController
//@Controller
public class MainController {

    @Autowired
    private EventServiceImpl eventService;

    @RequestMapping("/api")
    public String home() {
        return "index";
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
        return eventService.findBetween(start, end);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event createEvent(@RequestBody Event event) {

        return eventService.createEvent(event);
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public void moveEvent(@RequestBody Event event) {
        eventService.moveEvent(event.getId());
    }
    
    @PostMapping("/api/events/update")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public void deleteEvent(@RequestBody Event event) {
        eventService.updateEvent(event);
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    public void setColor( @RequestBody Event event) {
      eventService.setColor(event.getId(), event.getColor());
    }



}
