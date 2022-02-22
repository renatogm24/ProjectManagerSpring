package com.codingdojo.projectmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.projectmanager.models.Task;
import com.codingdojo.projectmanager.repositories.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepo;
	
	public Task createTask(Task b) {
        return taskRepo.save(b);
    }
	
	public void deleteTasksByProjectId(Long id) {
        taskRepo.deleteByProjectowner_Id(id);
    }
	
}
