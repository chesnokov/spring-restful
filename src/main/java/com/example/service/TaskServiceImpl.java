package com.example.service;

import com.example.persistence.repository.TaskRepository;
import com.example.mapper.TaskMapper;
import com.example.persistence.model.TaskData;

import com.example.service.entity.TaskEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	private final TaskRepository taskRepository;
	private final TaskMapper taskMapper;

	@Transactional(readOnly = true)
	@Override
	public List<TaskEntity> getAllTasks() {
		List<TaskData> taskData = taskRepository.findAll();
		return taskData.stream()
				.map( taskMapper::taskDataToTask).toList();
	}

	@Transactional(readOnly = true)
	@Override
	public Optional<TaskEntity> getTaskById(Long id) {
		Optional<TaskData> taskData = taskRepository.findById(id);
		return taskData.map(taskMapper::taskDataToTask);
	}

	@Transactional
	@Override
	public TaskEntity addTask(TaskEntity task) {
		TaskData taskData = taskMapper.taskToTaskData(task);
		taskData = taskRepository.save(taskData);
		return taskMapper.taskDataToTask(taskData);
	}

	@Transactional
	@Override
	public Optional<TaskEntity> updateTask(Long id, TaskEntity task) {
		Optional<TaskData> taskData = taskRepository.findById(id);
		if(taskData.isEmpty()) {
			return Optional.empty();
		} else {
			TaskData updatedTask = taskMapper.taskToTaskData(task);
			updatedTask.setId(id);
			updatedTask = taskRepository.save(updatedTask);
			return Optional.ofNullable(taskMapper.taskDataToTask(updatedTask));
		}
	}

	@Transactional
	@Override
	public void deleteTask(Long id) {
		taskRepository.deleteById(id);
	}
}
