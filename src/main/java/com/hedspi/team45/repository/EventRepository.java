package com.hedspi.team45.repository;

import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.hedspi.team45.domain.Event;

@Transactional
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
	public List<Event> findBetween(@Param("from") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar start, @Param("to") @DateTimeFormat(pattern = "yyyy-MM-dd") Calendar end);
}