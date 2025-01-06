package com.plannerapp.controller;

import com.plannerapp.model.dto.AddTaskDTO;
import com.plannerapp.service.CurrentUser;
import com.plannerapp.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AddTaskController {

    private final CurrentUser currentUser;
    private final TaskService taskService;

    public AddTaskController(CurrentUser currentUser, TaskService taskService) {
        this.currentUser = currentUser;
        this.taskService = taskService;
    }

    @ModelAttribute("addTaskDTO")
    public AddTaskDTO addTaskDTO() {
        return new AddTaskDTO();
    }

    @GetMapping("/tasks/add")
    public String addTask() {
        if (!currentUser.isLoggedIn()){
            return "redirect:/users/login";
        }
        return "task-add";
    }

    @PostMapping("/tasks/add")
    public String addWord(@Valid AddTaskDTO addTaskDTO,
                          BindingResult result,
                          RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("addTaskDTO", addTaskDTO);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.addTaskDTO", result);
            return "redirect:/tasks/add";
        }

        taskService.addTask(addTaskDTO);

        return "redirect:/home";
    }
}
