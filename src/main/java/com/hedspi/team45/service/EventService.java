package com.hedspi.team45.service;

import java.time.LocalDateTime;

import com.hedspi.team45.entity.Event;

public interface EventService {
	
	Event createEvent(Event event);
	
	void moveEvent(long id);
	
	void setColor(long id,String color);
	
	void updateEvent(Event event);
	
	Iterable<Event> findBetween(LocalDateTime start, LocalDateTime end);

}
