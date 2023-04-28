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
        todoService.addTodo("Test todo 1", "Test description 1", 2, new Date(), "Pending", "");
        todoService.addTodo("Test todo 2", "Test description 2", 3, new Date(), "Done", "");
        todoService.addTodo("Test todo 3", "Test description 3", 1, new Date(), "Pending", "");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DATE, 18);
        testDate = calendar.getTime();
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testRetrieveTodos() {
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");
        assertEquals(3, todos.size());

        todos = todoService.retrieveTodos("testuser");
        assertEquals(0, todos.size());

        todos = todoService.retrieveTodos("");
        assertEquals(0, todos.size());

        todos = todoService.retrieveTodos(null);
        assertEquals(0, todos.size());
    }

    @Test
    public void testRetrieveDailyHours() {
        int dailyHours = todoService.retrieveDailyHours("DemoAdmin", testDate);
        assertEquals(6, dailyHours);

        dailyHours = todoService.retrieveDailyHours("testuser", testDate);
        assertEquals(0, dailyHours);

        dailyHours = todoService.retrieveDailyHours("", testDate);
        assertEquals(0, dailyHours);

        dailyHours = todoService.retrieveDailyHours(null, testDate);
        assertEquals(0, dailyHours);
    }

    @Test
    public void testRetrieveWeeklyHours() {
        int weeklyHours = todoService.retrieveWeeklyHours("DemoAdmin", testDate);
        assertEquals(6, weeklyHours);

        weeklyHours = todoService.retrieveWeeklyHours("testuser", testDate);
        assertEquals(0, weeklyHours);

        weeklyHours = todoService.retrieveWeeklyHours("", testDate);
        assertEquals(0, weeklyHours);

        weeklyHours = todoService.retrieveWeeklyHours(null, testDate);
        assertEquals(0, weeklyHours);
    }

    @Test
    public void testGetDatesOfWeek() {
        List<Date> dates = todoService.getDatesOfWeek(testDate);
        assertEquals(5, dates.size());

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(testDate);
        assertEquals(calendar.get(Calendar.DATE), dates.get(2).getDate());

        // Test for different days of the week
        calendar.set(Calendar.DATE, 19);
        testDate = calendar.getTime();
        dates = todoService.getDatesOfWeek(testDate);
        assertEquals(5, dates.size());
        assertEquals(calendar.get(Calendar.DATE), dates.get(3).getDate());
    }

    @Test
    public void testRetrieveTodo() {
        Todo todo = todoService.retrieveTodo(1);
        assertNotNull(todo);
        assertEquals("Test todo 1", todo.getUser());

        todo = todoService.retrieveTodo(100);
        assertNull(todo);
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
        Todo todo = todoService.retrieveTodo(1);
        assertNotNull(todo);
    }

}
