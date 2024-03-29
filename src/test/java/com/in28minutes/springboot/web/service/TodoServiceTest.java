package com.in28minutes.springboot.web.service;

import static org.junit.Assert.*;

import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.util.*;

import com.in28minutes.springboot.web.SpringBootFirstWebApplication;
import com.in28minutes.springboot.web.model.Todo;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

public class TodoServiceTest {
    private static TodoService todoService;
    private Date testDate;

    private static int todoCnt = 0;

    @Before
    public void setUp() {
        todoService = new TodoService();
        todoService.addTodo("DemoAdmin", "Test description 1", 2, new Date(), "Pending", "");
        todoService.addTodo("DemoAdmin", "Test description 2", 3, new Date(), "Done", "");
        todoService.addTodo("DemoAdmin", "Test description 3", 1, new Date(), "Pending", "");
        todoCnt += 3;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE));
        calendar.setTimeZone(TimeZone.getTimeZone("EST"));
        testDate = calendar.getTime();
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testRetrieveTodos() {
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");
//        assertEquals(todoCnt + 1, todos.size());
    }

    @Test
    public void testRetrieveDailyHours() {
        int dailyHours = todoService.retrieveDailyHours("DemoAdmin", testDate);
        assertEquals(30, dailyHours);
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

//        Calendar calendar = Calendar.getInstance();
//        Calendar calendar1 = Calendar.getInstance();
//        calendar.setTime(testDate);
//        calendar1.setTime(dates.get(2));
//        assertEquals(calendar.get(Calendar.DAY_OF_MONTH), calendar1.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testRetrieveTodo() {
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");

        // Test Retrieve of Valid Id
        int id = todos.get(0).getId();
        Todo todo = todoService.retrieveTodo(id);
        assertNotNull(todo);
        assertEquals("DemoAdmin", todo.getUser());

        // Test Retrieve of Invalid Id
        todos = todoService.retrieveTodos("DemoAdmin");
        int invalidToDoId = 0;
        List<Integer> ids = new ArrayList<Integer>();
        for (Todo todoItr : todos) {
            ids.add(todoItr.getId());
        }
        Collections.sort(ids);
        invalidToDoId = ids.get(ids.size() - 1) + 1;
        todo = todoService.retrieveTodo(invalidToDoId);
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
        List<Todo> todos = todoService.retrieveTodos("DemoAdmin");
        int todoSizeBefore = todos.size();
        todoService.deleteTodo(1);
        todos = todoService.retrieveTodos("DemoAdmin");
        assertEquals(todoSizeBefore - 1, todos.size());
    }

}
