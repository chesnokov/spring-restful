package com.example.mapper;

import com.example.persistence.model.TaskData;
import com.example.rest.model.RestTask;
import com.example.service.entity.TaskEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
	TaskEntity restTaskToTask(RestTask restTask);
	TaskEntity taskDataToTask(TaskData taskData);
	RestTask taskToRestTask(TaskEntity task);
	TaskData taskToTaskData(TaskEntity task);
}
