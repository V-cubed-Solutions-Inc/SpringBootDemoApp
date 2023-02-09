package com.in28minutes.springboot.web.model;

import java.util.Date;

public class Todo {
    private int id;
    private String user;
    private String description;
    private int hoursRequired;
    private Date date;
    private String status;
    private String notes;

    public Todo() {
        super();
    }

    public Todo(
            int id,
            String user,
            String description,
            int hoursRequired,
            Date date,
            String status,
            String notes
    ) {
        super();
        this.id = id;
        this.user = user;
        this.description = description;
        this.date = date;
        this.status = status;
        this.notes = notes;
        this.hoursRequired = hoursRequired;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getHoursRequired() {
        return hoursRequired;
    }

    public void setHoursRequired(int hoursRequired) {
        this.hoursRequired = hoursRequired;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Todo other = (Todo) obj;
        return id == other.id;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo [id=%s, user=%s, description=%s, hoursRequired=%s, date=%s, status=%s, notes=%s]",
                id, user, description, hoursRequired, date, status, notes);
    }

}