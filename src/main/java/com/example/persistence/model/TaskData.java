package com.example.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "task")
public class TaskData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name="user_id")
	private UserData user;

	@Column(name = "taskname")
	private String name;

	@Column(name="description")
	private String description;

	@Column(name = "creation_date")
	private LocalDate creationDate;

	@Column(name = "dead_line")
	private LocalDate deadLine;
}
