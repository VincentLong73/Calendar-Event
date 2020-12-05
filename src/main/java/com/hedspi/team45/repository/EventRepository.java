package com.hedspi.team45.repository;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.hedspi.team45.entity.Event;

@Transactional
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
	@Query("from Event e where not(e.end < :from or e.start > :to)")
	public List<Event> findBetween(@Param("from") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime start, @Param("to") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDateTime end);
}