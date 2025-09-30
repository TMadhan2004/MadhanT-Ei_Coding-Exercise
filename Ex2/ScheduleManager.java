import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Comparator;

public final class ScheduleManager 
{
    private static volatile ScheduleManager INSTANCE;

    private final List<Task> tasks = new ArrayList<>();
    private final List<ScheduleObserver> observers = new ArrayList<>();

    private ScheduleManager() {}

    public static ScheduleManager getInstance() 
    {
        if (INSTANCE == null) 
        {
            synchronized (ScheduleManager.class) 
            {
                if (INSTANCE == null)   
                   INSTANCE = new ScheduleManager();
            }
        }
        return INSTANCE;
    }

    public void addObserver(ScheduleObserver obs) 
    {
        if (obs != null) 
          observers.add(obs);
    }

    public boolean addTask(Task task) 
    {
        Objects.requireNonNull(task);
        for (Task t : tasks) 
        {
            if (task.overlaps(t)) 
            {
                notifyConflict(task, t);
                throw new IllegalArgumentException("Error: Task conflicts with existing task \"" + t.getDescription() + "\".");
            }
        }
        tasks.add(task);
        Collections.sort(tasks);
        notifyAdded(task);
        return true;
    }

    public boolean removeTask(String description) 
    {
        Task found = findByDescription(description);
        if (found == null) 
        {
            throw new IllegalArgumentException("Error: Task not found.");
        }
        tasks.remove(found);
        notifyRemoved(found);
        return true;
    }

    public boolean completeTask(String description) 
    {
        Task found = findByDescription(description);
        if (found == null) 
        {
            throw new IllegalArgumentException("Error: Task not found.");
        }
        if (found.isCompleted()) {
            throw new IllegalArgumentException("Error: Task is already completed.");
        }
        found.setCompleted(true);
        notifyCompleted(found);
        return true;
    }

    public boolean editTask(String oldDescription, Task newTask) 
    {
        Task existing = findByDescription(oldDescription);
        if (existing == null) 
        {
            throw new IllegalArgumentException("Error: Task not found.");
        }
        tasks.remove(existing);
        for (Task t : tasks) 
        {
            if (newTask.overlaps(t)) 
            {
                tasks.add(existing); 
                Collections.sort(tasks);
                notifyConflict(newTask, t);
                throw new IllegalArgumentException("Error: Task conflicts with existing task \"" + t.getDescription() + "\".");
            }
        }
        tasks.add(newTask);
        Collections.sort(tasks);
        notifyEdited(existing, newTask);
        return true;
    }

    public List<Task> getAllTasksSorted() {
        return Collections.unmodifiableList(tasks);
    }

    public List<Task> getTasksByPriority(Priority p) {
        List<Task> list = new ArrayList<>();
        for (Task t : tasks) if (t.getPriority() == p) list.add(t);
        return list;
    }

    public List<Task> getAllTasksSortedByPriority() {
        List<Task> copy = new ArrayList<>(tasks);
        copy.sort(Comparator
                .comparingInt((Task t) -> priorityRank(t.getPriority()))
                .thenComparing(Task::getStart)
                .thenComparing(t -> t.getDescription().toLowerCase()));
        return copy;
    }

    private int priorityRank(Priority p) {
        switch (p) {
            case HIGH: return 0;
            case MEDIUM: return 1;
            case LOW: return 2;
            default: return 3;
        }
    }

    private Task findByDescription(String desc) 
    {
        if (desc == null) return null;
        String needle = desc.trim();
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(needle)) return t;
        }
        return null;
    }

    // New helpers
    public boolean hasActiveTasks() {
        for (Task t : tasks) if (!t.isCompleted()) return true;
        return false;
    }

    public Task getTaskByDescription(String desc) {
        return findByDescription(desc);
    }

    private void notifyAdded(Task t) 
    { 
        for (ScheduleObserver o : observers)
             o.onTaskAdded(t); 
    
    }
    private void notifyRemoved(Task t) 
    {
         for (ScheduleObserver o : observers)
            o.onTaskRemoved(t); 
    }
    private void notifyCompleted(Task t) 
    { 
        for (ScheduleObserver o : observers) 
          o.onTaskCompleted(t); 
    }
    private void notifyEdited(Task oldT, Task newT) 
    { 
        for (ScheduleObserver o : observers) 
           o.onTaskEdited(oldT, newT); 
    }
    private void notifyConflict(Task a, Task b) 
    { 
        for (ScheduleObserver o : observers) 
           o.onConflict(a, b); 
    }
}
