package com.hedspi.team45.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="event")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="text")
	private String text;
	
	@Column(name="start")
	private LocalDateTime start;
	
	@Column(name="end")
	private LocalDateTime end;
	
	@Column(name="color")
	private String color;
	
	//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	@ManyToOne
	@JoinColumn(name="user_id")
	@EqualsAndHashCode.Exclude
    @ToString.Exclude
	private User user;

}
