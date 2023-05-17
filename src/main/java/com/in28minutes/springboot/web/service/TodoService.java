package com.in28minutes.springboot.web.service;

import com.in28minutes.springboot.web.model.Todo;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class TodoService {
    private static final List<Todo> todos = new ArrayList<Todo>();
    private static int todoCount = 0;

    public List<Todo> retrieveTodos(String user) {
        if (user.equals("DemoAdmin")) return todos;
        List<Todo> filteredTodos = new ArrayList<Todo>();
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user)) {
                filteredTodos.add(todo);
            }
        }
        return filteredTodos;
    }

    public Boolean isSameDay(Date current, Date reference) {
        Instant instant1 = current.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = reference.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }

    public int retrieveDailyHours(String user, Date date) {
        int dailyHours = 0;
        for (Todo todo : todos) {
            if (todo.getUser().equalsIgnoreCase(user) && isSameDay(date, todo.getDate())) {
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
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 2));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 4));
        } else if (dayOfWeek.equalsIgnoreCase("TUE")) {
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24));
            dates.add(date);
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 2));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 3));
        } else if (dayOfWeek.equalsIgnoreCase("WED")) {
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 2));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24));
            dates.add(date);
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24));
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24 * 2));
        } else if (dayOfWeek.equalsIgnoreCase("THU")) {
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 3));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 2));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24));
            dates.add(date);
            dates.add(new Date(date.getTime() + 1000 * 60 * 60 * 24));
        } else if (dayOfWeek.equalsIgnoreCase("FRI")) {
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 4));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 3));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24 * 2));
            dates.add(new Date(date.getTime() - 1000 * 60 * 60 * 24));
            dates.add(date);
        }
        return dates;
    }

    public Todo retrieveTodo(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
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
        UUID uuid = UUID.randomUUID();
        String uuidStr = uuid.toString();
        description = description.concat("\n" + uuidStr);

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