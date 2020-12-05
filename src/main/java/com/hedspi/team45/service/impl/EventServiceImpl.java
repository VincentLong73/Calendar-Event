package com.hedspi.team45.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedspi.team45.entity.Event;
import com.hedspi.team45.repository.EventRepository;
import com.hedspi.team45.service.EventService;

@Service
public class EventServiceImpl implements EventService{
	
	@Autowired
	private EventRepository eventRepository;

	@Override
	public Event createEvent(Event event) {
		Event e = new Event();
        e.setStart(event.getStart());
        e.setEnd(event.getEnd());
        e.setText(event.getText());
        e.setColor(event.getColor());

        eventRepository.save(e);
        return e;
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
	public Iterable<Event> findBetween(LocalDateTime start, LocalDateTime end) {
		
		return eventRepository.findBetween(start, end);
	}

	@Override
	public void updateEvent(Event event) {
		Event updateE = eventRepository.getOne(event.getId());
		updateE.setStart(event.getStart());
		updateE.setEnd(event.getEnd());
		eventRepository.saveAndFlush(updateE);
	}
	
	

}
