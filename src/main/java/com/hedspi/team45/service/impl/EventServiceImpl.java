package com.hedspi.team45.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedspi.team45.domain.Event;
import com.hedspi.team45.repository.EventRepository;
import com.hedspi.team45.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public void createEvent(Event event) {
		Event e = new Event();
        e.setStart(event.getStart());
        e.setEnd(event.getEnd());
        e.setText(event.getText());
        e.setColor(event.getColor());

        eventRepository.save(e);
	}

	@Override
	public void moveEvent(long id) {
		eventRepository.deleteById(id);
		
		
	}

	@Override
	public void setColor(long id,String color) {
		
		Event e = eventRepository.getOne(id);
		e.setColor(color);
		eventRepository.saveAndFlush(e);
	}

	@Override
	public Iterable<Event> findBetween(Calendar start, Calendar end) {
		
		return eventRepository.findBetween(start, end);
	}
	
	

}
