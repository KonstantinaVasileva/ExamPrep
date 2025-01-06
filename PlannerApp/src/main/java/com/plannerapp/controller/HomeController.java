package com.plannerapp.controller;

import com.plannerapp.model.entity.Task;
import com.plannerapp.service.CurrentUser;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class HomeController {

    private final CurrentUser currentUser;
    private final TaskService taskService;

    public HomeController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @GetMapping("/")
    public String home() {
        if (currentUser.isLoggedIn()){
            return "redirect:/home";
        }
        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!currentUser.isLoggedIn()){
            return "redirect:/";
        }

        List<Task> allAvailableTasks = taskService.getAllAvailableTasks();
        model.addAttribute("allAvailableTasks", allAvailableTasks);
        List<Task> myTasks = taskService.getMyTasks();
        model.addAttribute("myTasks", myTasks);

        return "home";
    }

    @GetMapping("/home/remove/{id}")
    public String removeTask(@PathVariable long id) {
        taskService.removeTaskById(id);
        return "redirect:/home";
    }

    @GetMapping("/home/return/{id}")
    public String returnTask(@PathVariable long id) {
        taskService.returnTaskById(id);
        return "redirect:/home";
    }

    @GetMapping("/home/add/{id}")
    public String addTask(@PathVariable long id) {
        taskService.addTaskById(id);
        return "redirect:/home";
    }
}
