package com.example.todo_assignment;

public class Projects {
    String title;
    String description;
    private int priority;

    public Projects(String title, String description, int priority)
    {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public Projects() {
    }

    public int getPriority() {
        return priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
