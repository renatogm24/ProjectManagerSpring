package com.codingdojo.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.codingdojo.projectmanager.models.Task;



@Repository
public interface TaskRepository extends CrudRepository<Task,Long> {
	List<Task> findAll();
	
	@Transactional
	void deleteByProjectowner_Id(Long id);
}
