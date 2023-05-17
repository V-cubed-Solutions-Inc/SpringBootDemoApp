package com.in28minutes.springboot.web.service;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.in28minutes.springboot.web.model.Todo;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;

public class TodoServiceTest {
    private TodoService todoService;
    private Date testDate;

    @Before
    public void setUp() {
        todoService = new TodoService();
        todoService.addTodo("DemoAdmin", "Test description 1", 2, new Date(), "Pending", "");
        todoService.addTodo("DemoAdmin", "Test description 2", 3, new Date(), "Done", "");
        todoService.addTodo("DemoAdmin", "Test description 3", 1, new Date(), "Pending", "");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        testDate = calendar.getTime();
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRetrieveTodos() {
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");
        assertEquals(3, todos.size());
    }

    @Test
    public void testRetrieveDailyHours() {
        int dailyHours = todoService.retrieveDailyHours("DemoAdmin", testDate);
        assertEquals(6, dailyHours);
    }

    @Test
    public void testRetrieveWeeklyHours() {
        int weeklyHours = todoService.retrieveWeeklyHours("DemoAdmin", testDate);
        assertEquals(6, weeklyHours);
    }

    @Test
    public void testGetDatesOfWeek() {
        List<Date> dates = todoService.getDatesOfWeek(testDate);
        assertEquals(5, dates.size());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(testDate);
        assertEquals(calendar.get(Calendar.DATE), dates.get(2).getDate());
    }

    @Test
    public void testRetrieveTodo() {
        Todo todo = todoService.retrieveTodo(1);
        assertNotNull(todo);
        assertEquals("DemoAdmin", todo.getUser());
//
//        todo = todoService.retrieveTodo(100);
//        assertNull(todo);
    }

    @Test
    public void testUpdateTodo() {
        Todo todo = todoService.retrieveTodo(1);
        assertNotNull(todo);

        todo.setDescription("Updated description");
        todoService.updateTodo(todo);

        Todo updatedTodo = todoService.retrieveTodo(1);
        assertNotNull(updatedTodo);
        assertEquals("Updated description", updatedTodo.getDescription());
    }

    @Test
    public void deleteTodo() {
        todoService.deleteTodo(1);
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");
        assertEquals(2, todos.size());
    }

}
