package com.codingdojo.projectmanager.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.repositories.ProjectRepository;
import com.codingdojo.projectmanager.repositories.UserRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepo;

	@Autowired
	private UserRepository userRepo;

	public List<Project> findByUsersNotContains(User user) {
		return projectRepo.findByUsersNotContains(user);
	}

	public Project createProject(Project b) {
		return projectRepo.save(b);
	}

	public Project updateProject(Project b) {
		return projectRepo.save(b);
	}

	public Project findProject(Long id) {
		Optional<Project> optionalProject = projectRepo.findById(id);
		if (optionalProject.isPresent()) {
			return optionalProject.get();
		} else {
			return null;
		}
	}

	public Project addUserToProject(Long projectId, Long userId) {
		Project thisProject = projectRepo.findById(projectId).get();
		User thisUser = userRepo.findById(userId).get();
		if (thisProject.getUsers() != null) {
			thisProject.getUsers().add(thisUser);
		} else {
			List<User> listUser = new ArrayList<User>();
			listUser.add(thisUser);
			thisProject.setUsers(listUser);
		}

		return projectRepo.save(thisProject);
	}

	public void deleteProject(Long id) {
		projectRepo.deleteById(id);
	}

}
