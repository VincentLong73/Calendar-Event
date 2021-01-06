package com.hedspi.team45.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

//	@RequestMapping("/index")
//	public String home() {
//		return "welcome";
//	}

    @GetMapping("/api/events/{userId}")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start, @RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end,
    	 @PathVariable("userId") int userId) {
    	System.out.println("42 : "+userId);
        return eventService.findBetweenAndUserId(start, end,userId);
    }

//	@GetMapping("/api/events")
//	@JsonSerialize(using = LocalDateTimeSerializer.class)
//	Iterable<Event> events(@RequestParam("start") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime start,
//			@RequestParam("end") @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime end) {
//
//		return eventService.findBetweenAndUserId(start, end, id);
//	}

	@PostMapping("/api/events/create/{userId}")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@Transactional
	Event createEvent(@RequestBody Event event,@PathVariable("userId") int userId) {
		System.out.println("58 : "+ userId);

		return eventService.createEvent(event, userId);
	}

	@PostMapping("/api/events/move")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@Transactional
	public void moveEvent(@RequestBody Event event) {
		eventService.moveEvent(event.getId());
	}

	@PostMapping("/api/events/update")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@Transactional
	public void deleteEvent(@RequestBody Event event) {
		eventService.updateEvent(event);
	}

	@PostMapping("/api/events/setColor")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	//@Transactional
	public void setColor(@RequestBody Event event) {
		eventService.setColor(event.getId(), event.getColor());
	}

}
