package com.in28minutes.springboot.web.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.in28minutes.springboot.web.model.Todo;

@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 3;

    static {
        Date today = new Date();
		Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        todos.add(
            new Todo(
                1, 
                "V-Cubed Solutions Inc.", 
                "Learn Spring MVC", 
                today, 
                tomorrow,
                "Not Started", 
                "Additional Notes"
            )
        );
        todos.add(
            new Todo(
                2, 
                "V-Cubed Solutions Inc.", 
                "Learn Hibernate", 
                today, 
                tomorrow,
                "Not Started", 
                "Additional Notes"
            )
        );
        todos.add(
            new Todo(
                2, 
                "V-Cubed Solutions Inc.", 
                "Learn Maven", 
                today, 
                tomorrow,
                "Not Started", 
                "Additional Notes"
            )
        );
    }

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }
    
    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId()==id) {
                return todo;
            }
        }
        return null;
    }

    public void updateTodo(Todo todo) {
    	todos.remove(todo);
        Date today = new Date();
        String status = "Not Started";
        if (today.after(todo.getStartDate()) && today.before(todo.getEndDate())) status = "In Progress";
        else if (today.after(todo.getEndDate())) status = "Due";
        todo.setStatus(status);
    	todos.add(todo);
    }

    public void addTodo(
        String name, 
        String description, 
        Date startDate, 
        Date endDate, 
        String notes
    ) {
        Date today = new Date();
        String status = "Not Started";
        if (today.after(startDate) && today.before(endDate)) status = "In Progress";
        else if (today.equals(endDate)) status = "Due";
        else if (today.after(endDate)) status = "Overdue";

        todos.add(
            new Todo(
                ++todoCount, 
                name, 
                description, 
                startDate, 
                endDate, 
                status, 
                notes
            )
        );
    }

    public void deleteTodo(int id) {
        Iterator<Todo> iterator = todos.iterator();
        while (iterator.hasNext()) {
            Todo todo = iterator.next();
            if (todo.getId() == id) {
                iterator.remove();
            }
        }
    }
}