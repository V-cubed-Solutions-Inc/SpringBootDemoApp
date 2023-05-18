package com.in28minutes.springboot.web.controller;

import com.in28minutes.springboot.web.model.Todo;
import com.in28minutes.springboot.web.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class TodoController {

    @Autowired
    TodoService service;

    private Boolean overtimeAllowed = false;

    @RequestMapping(value = "/enable-overtime", method = RequestMethod.GET)
    public String enableOvertime() {
        overtimeAllowed = true;
        return "user-management";
    }

    @RequestMapping(value = "/disable-overtime", method = RequestMethod.GET)
    public String disableOvertime() {
        overtimeAllowed = false;
        return "user-management";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - EEE, MMM d yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM d yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                dateFormat, false));
    }

    @RequestMapping(value = "/list-todos", method = RequestMethod.GET)
    public String showTodos(ModelMap model) {
        String name = getLoggedInUserName();
        model.put("todos", service.retrieveTodos(name));
        return "list-todos";
    }

    private String getLoggedInUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute(
                "todo",
                new Todo(
                        0, getLoggedInUserName(),
                        "Description",
                        1,
                        new Date(),
                        "Not Started",
                        "Additional Notes"
                )
        );
        return "todo";
    }

    @RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
    public String deleteTodo(@RequestParam int id) {
        service.deleteTodo(id);
        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = service.retrieveTodo(id);
        model.put("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "/update-todo", method = RequestMethod.POST)
    public String updateTodo(@Valid Todo todo,
                             BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        todo.setUser(getLoggedInUserName());

        service.updateTodo(todo);
        int dailyHours = service.retrieveDailyHours(getLoggedInUserName(), todo.getDate());
        int weeklyHours = service.retrieveWeeklyHours(getLoggedInUserName(), todo.getDate());
        if (dailyHours > 10 && !overtimeAllowed) {
            ObjectError error = new ObjectError("hoursRequired", "You already have 8 hours of work for the specified date and your manager doesn't allow you overtime.");
            result.rejectValue(error.getObjectName(), error.getCode(), error.getDefaultMessage());
        }
        if (weeklyHours > 40 && !overtimeAllowed) {
            ObjectError error = new ObjectError("hoursRequired", "You already have 40 hours of work for the specified week and your manager doesn't allow you overtime.");
            result.rejectValue(error.getObjectName(), error.getCode(), error.getDefaultMessage());
        }

        if (result.hasErrors()) {
            return "todo";
        }

        return "redirect:/list-todos";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid Todo todo, BindingResult result) {
        int dailyHours = service.retrieveDailyHours(getLoggedInUserName(), todo.getDate());
        int weeklyHours = service.retrieveWeeklyHours(getLoggedInUserName(), todo.getDate());
        if (dailyHours + todo.getHoursRequired() > 10 && !overtimeAllowed) {
            ObjectError error = new ObjectError("hoursRequired", "You already have 10 hours of work for the specified date and your manager doesn't allow you overtime.");
            result.rejectValue(error.getObjectName(), error.getCode(), error.getDefaultMessage());
        }
        if (weeklyHours + todo.getHoursRequired() > 40 && !overtimeAllowed) {
            ObjectError error = new ObjectError("hoursRequired", "You already have 40 hours of work for the specified week and your manager doesn't allow you overtime.");
            result.rejectValue(error.getObjectName(), error.getCode(), error.getDefaultMessage());
        }
        if (result.hasErrors()) {
            return "todo";
        }

        service.addTodo(
                getLoggedInUserName(),
                todo.getDescription(),
                todo.getHoursRequired(),
                todo.getDate(),
                todo.getStatus(),
                todo.getNotes()
        );
        return "redirect:/list-todos";
    }
}
