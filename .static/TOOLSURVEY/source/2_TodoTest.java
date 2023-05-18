package com.in28minutes.springboot.web.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import com.in28minutes.springboot.web.model.Todo;
import org.junit.Test;

public class TodoTest {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        int id = 1;
        String user = "John";
        String description = "Buy groceries";
        int hoursRequired = 2;
        Date date = new Date();
        String status = "TODO";
        String notes = "Remember to buy milk";

        Todo todo = new Todo();

        // Act
        todo.setId(id);
        todo.setUser(user);
        todo.setDescription(description);
        todo.setHoursRequired(hoursRequired);
        todo.setDate(date);
        todo.setStatus(status);
        todo.setNotes(notes);

        // Assert
        assertEquals(id, todo.getId());
        assertEquals(user, todo.getUser());
        assertEquals(description, todo.getDescription());
        assertEquals(hoursRequired, todo.getHoursRequired());
        assertEquals(date, todo.getDate());
        assertEquals(status, todo.getStatus());
        assertEquals(notes, todo.getNotes());
    }

    @Test
    public void testEquals() {
        // Arrange
        Todo todo1 = new Todo(1, "John", "Buy groceries", 2, new Date(), "TODO", "Remember to buy milk");
        Todo todo2 = new Todo(1, "John", "Buy groceries", 2, new Date(), "TODO", "Remember to buy milk");
        Todo todo3 = new Todo(2, "Jane", "Clean the house", 4, new Date(), "TODO", "Sweep the floor");

        // Assert
        assertTrue(todo1.equals(todo2));
        assertFalse(todo1.equals(todo3));
    }

    @Test
    public void testHashCode() {
        // Arrange
        Todo todo1 = new Todo(1, "John", "Buy groceries", 2, new Date(), "TODO", "Remember to buy milk");
        Todo todo2 = new Todo(1, "John", "Buy groceries", 2, new Date(), "TODO", "Remember to buy milk");

        // Assert
        assertEquals(todo1.hashCode(), todo2.hashCode());
    }

    @Test
    public void testToString() {
        // Arrange
        Todo todo = new Todo(1, "John", "Buy groceries", 2, new Date(), "TODO", "Remember to buy milk");

        // Assert
        assertEquals("Todo [id=1, user=John, description=Buy groceries, hoursRequired=2, date=" + todo.getDate() + ", status=TODO, notes=Remember to buy milk]", todo.toString());
    }

}
