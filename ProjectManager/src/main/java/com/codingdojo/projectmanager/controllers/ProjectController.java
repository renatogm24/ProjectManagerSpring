package com.codingdojo.projectmanager.controllers;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.codingdojo.projectmanager.models.Project;
import com.codingdojo.projectmanager.models.Task;
import com.codingdojo.projectmanager.models.User;
import com.codingdojo.projectmanager.services.ProjectService;
import com.codingdojo.projectmanager.services.TaskService;
import com.codingdojo.projectmanager.services.UserService;

@Controller
public class ProjectController {

	@Autowired
	private UserService userServ;

	@Autowired
	private ProjectService projectServ;

	@Autowired
	private TaskService taskServ;

	@RequestMapping("/projects/new")
	public String newBook(@ModelAttribute("project") Project project, HttpSession session) {
		Long id = (Long) session.getAttribute("userid");
		if(id == null) {
			return "redirect:/";
		}
		
		return "/projects/new.jsp";
	}

	@RequestMapping(value = "/projects", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("project") Project project, BindingResult result, Model model,
			HttpSession session) {
	
		Long id = (Long) session.getAttribute("userid");
		if(id == null) {
			return "redirect:/";
		}

		if (project.getDueDate().before(new Date(System.currentTimeMillis()))) {
			result.rejectValue("dueDate", "Matches", "The Date is before the actual date");
		}

		if (result.hasErrors()) {
			return "/projects/new.jsp";
		}

		Long user_id = (Long) session.getAttribute("userid");

		project.setLeader(userServ.getUserSession(user_id));

		Project projectAux = projectServ.createProject(project);

		projectServ.addUserToProject(projectAux.getId(), user_id);

		return "redirect:/";
	}

	@RequestMapping("/projects/{id}")
	public String show(Model model, @PathVariable("id") Long id, HttpSession session) {
		Long idSession = (Long) session.getAttribute("userid");
		if(idSession == null) {
			return "redirect:/";
		}
		
		Project project = projectServ.findProject(id);
		model.addAttribute("project", project);
		return "/projects/show.jsp";
	}

	@RequestMapping("/projects/{id}/tasks")
	public String showTasks(Model model, @PathVariable("id") Long id, @ModelAttribute("task") Task task, HttpSession session) {
		Long idSession = (Long) session.getAttribute("userid");
		if(idSession == null) {
			return "redirect:/";
		}
		
		Project project = projectServ.findProject(id);
		model.addAttribute("project", project);
		return "/projects/tasks.jsp";
	}

	@RequestMapping(value = "/projects/{id}/addTask", method = RequestMethod.POST)
	public String createTask(@Valid @ModelAttribute("task") Task task, @PathVariable("id") Long id,
			BindingResult result, Model model, HttpSession session) {

		Long idSession = (Long) session.getAttribute("userid");
		if(idSession == null) {
			return "redirect:/";
		}
		
		if (result.hasErrors()) {
			return "/projects/tasks.jsp";
		}

		Long user_id = (Long) session.getAttribute("userid");
		task.setAuthor(userServ.getUserSession(user_id));

		Project projectAux = projectServ.findProject(id);
		task.setProjectowner(projectAux);
		
		task.setId(null);

		taskServ.createTask(task);

		return "redirect:/projects/" + id + "/tasks";
	}

	@GetMapping("/projects/{id}/delete")
	public String destroy(@PathVariable("id") Long id, HttpSession session) {
		Long idSession2 = (Long) session.getAttribute("userid");
		if(idSession2 == null) {
			return "redirect:/";
		}
		
		Project projectAux = projectServ.findProject(id);
		Long idsession = (Long) session.getAttribute("userid");
		Long iduser = (Long) projectAux.getLeader().getId();
		if (iduser.equals(idsession)) {
			taskServ.deleteTasksByProjectId(id);
			projectServ.deleteProject(id);
			return "redirect:/";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/projects/{id}/jointeam")
	public String jointeam(Model model, @PathVariable("id") Long id, HttpSession session) {
		Long idSession = (Long) session.getAttribute("userid");
		if(idSession == null) {
			return "redirect:/";
		}
		
		Long user_id = (Long) session.getAttribute("userid");
		projectServ.addUserToProject(id, user_id);
		return "redirect:/";
	}

	@RequestMapping("/projects/{id}/leaveteam")
	public String leaveteam(Model model, @PathVariable("id") Long id, HttpSession session) {
		
		Long idSession = (Long) session.getAttribute("userid");
		if(idSession == null) {
			return "redirect:/";
		}

		Long user_id = (Long) session.getAttribute("userid");
		User userAux = userServ.getUserSession(user_id);

		Project projectToDelete = projectServ.findProject(id);

		userAux.getProjects().remove(projectToDelete);

		userServ.updateUser(userAux);

		return "redirect:/";
	}

	@GetMapping("/projects/{id}/edit")
	public String edit(@PathVariable("id") Long id, Model model, HttpSession session) {
		Long idSession2 = (Long) session.getAttribute("userid");
		if(idSession2 == null) {
			return "redirect:/";
		}
		
		Project project = projectServ.findProject(id);
		Long idsession = (Long) session.getAttribute("userid");
		Long iduser = (Long) project.getLeader().getId();
		if (iduser.equals(idsession)) {
			model.addAttribute("project", project);
			return "/projects/edit.jsp";
		} else {
			return "redirect:/";
		}
	}

	@PutMapping("/projects/{id}")
	public String update(@Valid @ModelAttribute("project") Project project, BindingResult result, HttpSession session) {
		
		Long idSession2 = (Long) session.getAttribute("userid");
		if(idSession2 == null) {
			return "redirect:/";
		}
		
		Long idsession = (Long) session.getAttribute("userid");
		Long iduser = (Long) project.getLeader().getId();
		if (!iduser.equals(idsession)) {
			return "redirect:/";
		}

		if (project.getDueDate().before(new Date(System.currentTimeMillis()))) {
			result.rejectValue("dueDate", "Matches", "The Date is before the actual date");
		}

		if (result.hasErrors()) {
			return "/projects/edit.jsp";
		} else {
			Project projectAux = projectServ.findProject(project.getId());
			project.setUsers(projectAux.getUsers());
			projectServ.updateProject(project);
			return "redirect:/";
		}
	}

}
