package com.hedspi.team45.service;

import java.util.Calendar;

import com.hedspi.team45.domain.Event;

public interface EventService {
	
	void createEvent(Event event);
	
	void moveEvent(long id);
	
	void setColor(long id,String color);
	
	Iterable<Event> findBetween(Calendar start, Calendar end);

}
