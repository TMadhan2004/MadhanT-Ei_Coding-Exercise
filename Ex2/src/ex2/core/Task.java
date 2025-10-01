package ex2.core;

import java.time.LocalTime;
import java.util.Objects;

public class Task implements Comparable<Task> 
{
    private final String description;
    private final LocalTime start;
    private final LocalTime end;
    private final Priority priority;
    private boolean completed;

    public Task(String description, LocalTime start, LocalTime end, Priority priority) 
    {
        this.description = Objects.requireNonNull(description);
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        if (!end.isAfter(start)) throw new IllegalArgumentException("End time must be after start time");
        this.priority = Objects.requireNonNull(priority);
        this.completed = false;
    }

    public String getDescription() 
    { 
        return description; 
    }
    public LocalTime getStart() 
    { 
        return start; 
    }
    public LocalTime getEnd() 
    { 
        return end; 
    }
    public Priority getPriority() 
    { 
        return priority; 
    }
    public boolean isCompleted() 
    { 
        return completed; 
    }
    public void setCompleted(boolean completed) 
    { 
        this.completed = completed; 
    }
    public boolean overlaps(Task other) 
    {
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }

    @Override
    public int compareTo(Task o) 
    {
        int c = this.start.compareTo(o.start);
        if (c != 0) 
           return c;
        return this.description.compareToIgnoreCase(o.description);
    }

    @Override
    public String toString() 
    {
        String status = completed ? "[Done] " : "";
        return String.format("%s%s - %s: %s [%s]", status, start, end, description, priority);
    }
}
