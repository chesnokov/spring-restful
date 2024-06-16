package com.example.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
	private Long id;

	private String name;

	private String description;

	private LocalDate creationDate;

	private LocalDate deadLine;
}
