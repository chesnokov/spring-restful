package com.example.persistence.repository;

import com.example.HelloWorldConfiguration;
import com.example.persistence.model.TaskData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;

import java.time.LocalDate;

@SpringJUnitWebConfig(classes = { TaskRepository.class })
@ContextConfiguration(classes = {TestConfig.class, HelloWorldConfiguration.class})
@ActiveProfiles("testing")
public class TestTaskRepository {

	@Autowired
	private TaskRepository taskRepository;

	@Test
	public void test() {
		TaskData task1 = new TaskData();
		task1.setName("Task1");
		task1.setDescription("TaskDescription1");
		task1.setCreationDate(LocalDate.of(2024, 5, 14 ));
		task1.setDeadLine(LocalDate.of(2024, 6, 1));
		task1 = taskRepository.save(task1);
	}

}
