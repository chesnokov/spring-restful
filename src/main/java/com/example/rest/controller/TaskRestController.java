package com.example.rest.controller;


import com.example.mapper.TaskMapper;
import com.example.rest.model.RestTask;
import com.example.service.TaskService;
import com.example.service.entity.TaskEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class TaskRestController {

	private final TaskService taskService;
	private final TaskMapper taskMapper;

	/**
	 * GET /task : получить список всех задач
	 *
	 * @return список задач успешно получен (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/task",
			produces = { "application/json" }
	)
	public ResponseEntity<List<RestTask>> getAllTasks() {
		List<TaskEntity> tasks = taskService.getAllTasks();
		List<RestTask> restTasks = tasks.stream().map(taskMapper::taskToRestTask).toList();
		return new ResponseEntity<>(restTasks, HttpStatus.OK);
	}

	/**
	 * GET /task/{taskId} : получить задачу по идентификатору
	 *
	 * @param taskId идентификатор задачи (required)
	 * @return задача найдена успешно (status code 200)
	 *         or задача не найдена (status code 404)
	 */
	@RequestMapping(
			method = RequestMethod.GET,
			value = "/task/{taskId}",
			produces = { "application/json" }
	)
	public ResponseEntity<RestTask> getTaskById(@PathVariable("taskId") Long taskId) {
		Optional<TaskEntity> task = taskService.getTaskById(taskId);
		if(task.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			RestTask restTask = taskMapper.taskToRestTask(task.get());
			return new ResponseEntity<>(restTask, HttpStatus.OK);
		}
	}

	/**
	 * POST /task : добавить задачу
	 *
	 * @param restTask  (required)
	 * @return задача создана успешно (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.POST,
			value = "/task",
			produces = { "application/json" },
			consumes = { "application/json" }
	)
	public ResponseEntity<RestTask> addTask(@RequestBody RestTask restTask) {
		TaskEntity task = taskMapper.restTaskToTask(restTask);
		task = taskService.addTask(task);
		RestTask resultTask = taskMapper.taskToRestTask(task);
		return new ResponseEntity<>(resultTask, HttpStatus.OK);
	}

	/**
	 * PUT /task/{taskId} : модифицировать задачу по идентификатору
	 *
	 * @param taskId идентификатор задачи (required)
	 * @param restTask  (required)
	 * @return задача модифицирована успешно (status code 200)
	 *         or задача не найдена (status code 404)
	 */
	@RequestMapping(
			method = RequestMethod.PUT,
			value = "/task/{taskId}",
			produces = { "application/json" },
			consumes = { "application/json" }
	)
	public ResponseEntity<RestTask> updateTask(@PathVariable("taskId") Long taskId, @RequestBody RestTask restTask) {
		TaskEntity task = taskMapper.restTaskToTask(restTask);
		Optional<TaskEntity> updatedTask = taskService.updateTask(taskId, task);

		if(updatedTask.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			RestTask resultTask = taskMapper.taskToRestTask(updatedTask.get());
			return new ResponseEntity<>(resultTask, HttpStatus.OK);
		}
	}

	/**
	 * DELETE /task/{taskId} : удалить задачу по идентификатору
	 *
	 * @param taskId идентификатор задачи (required)
	 * @return задача удалена успешно (status code 200)
	 */
	@RequestMapping(
			method = RequestMethod.DELETE,
			value = "/task/{taskId}"
	)
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") Long taskId) {
		taskService.deleteTask(taskId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
