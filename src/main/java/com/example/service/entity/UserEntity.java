package com.example.service.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
	private Long id;
	private String name;

	private String surname;

	private String mail;

	private List<TaskEntity> tasks=new ArrayList<>();
}
