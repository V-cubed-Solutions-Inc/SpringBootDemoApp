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
    private static int todoCount = 0;

    public List<Todo> retrieveTodos(String user) {
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public int retrieveDailyHours(String user, Date date) {
        int dailyHours = 0; 
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user) && date.equals(todo.getDate())) {
                dailyHours += todo.getHoursRequired();
            }
        }
        return dailyHours;
    }

    public int retrieveWeeklyHours(String user, Date date) {
        int weeklyHours = 0; 
        List<Date> datesOfWeek = getDatesOfWeek(date);
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user) && datesOfWeek.contains(date)) {
                weeklyHours += todo.getHoursRequired();
            }
        }
        return weeklyHours;
    }

    public List<Date> getDatesOfWeek(Date date) {
        List<Date> dates = new ArrayList<Date>();
        String dayOfWeek = date.toString().substring(0, 3);
        if (dayOfWeek.equalsIgnoreCase("MON")) {
            dates.add(date);
            dates.add(new Date(date.getTime() + 60*60*24*1));
            dates.add(new Date(date.getTime() + 60*60*24*2));
            dates.add(new Date(date.getTime() + 60*60*24*3));
            dates.add(new Date(date.getTime() + 60*60*24*4));
        }
        else if (dayOfWeek.equalsIgnoreCase("TUE")) {
            dates.add(new Date(date.getTime() - 60*60*24*1));
            dates.add(date);
            dates.add(new Date(date.getTime() + 60*60*24*1));
            dates.add(new Date(date.getTime() + 60*60*24*2));
            dates.add(new Date(date.getTime() + 60*60*24*3));
        }
        else if (dayOfWeek.equalsIgnoreCase("WED")) {
            dates.add(new Date(date.getTime() - 60*60*24*2));
            dates.add(new Date(date.getTime() - 60*60*24*1));
            dates.add(date);
            dates.add(new Date(date.getTime() + 60*60*24*1));
            dates.add(new Date(date.getTime() + 60*60*24*2));
        }
        else if (dayOfWeek.equalsIgnoreCase("THU")) {
            dates.add(new Date(date.getTime() - 60*60*24*3));
            dates.add(new Date(date.getTime() - 60*60*24*2));
            dates.add(new Date(date.getTime() - 60*60*24*1));
            dates.add(date);
            dates.add(new Date(date.getTime() + 60*60*24*1));
        }
        else if (dayOfWeek.equalsIgnoreCase("FRI")) {
            dates.add(new Date(date.getTime() - 60*60*24*4));
            dates.add(new Date(date.getTime() - 60*60*24*3));
            dates.add(new Date(date.getTime() - 60*60*24*2));
            dates.add(new Date(date.getTime() - 60*60*24*1));
            dates.add(date);
        }
        return dates;
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
    	todos.add(todo);
    }

    public void addTodo(
        String name, 
        String description,
        int hoursRequired, 
        Date date,
        String status,
        String notes
    ) {
        todos.add(
            new Todo(
                ++todoCount, 
                name, 
                description,
                hoursRequired, 
                date,
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