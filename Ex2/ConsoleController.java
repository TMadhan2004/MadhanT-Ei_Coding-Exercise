import java.util.List;
import java.util.Scanner;

public class ConsoleController 
{
    private final ScheduleManager manager;
    private final TaskFactory factory;

    public ConsoleController(ScheduleManager manager, TaskFactory factory) 
    {
        this.manager = manager;
        this.factory = factory;
    }

    public boolean handle(String choice, Scanner sc)
    {
        switch (choice) 
        {
            case "1": addTask(sc); break;
            case "2": removeTask(sc); break;
            case "3": viewTasks(); break;
            case "4": editTask(sc); break;
            case "5": completeTask(sc); break;
            case "6": viewByPriority(sc); break;
            case "0": return false;
            default: System.out.println("Invalid option.\n");
        }
        return true;
    }

    private void addTask(Scanner sc) 
    {
        try 
        {
            System.out.print("Description: "); String desc = sc.nextLine();
            System.out.print("Start (HH:mm): "); String start = sc.nextLine();
            System.out.print("End (HH:mm): "); String end = sc.nextLine();
            System.out.print("Priority (High/Medium/Low): "); String pr = sc.nextLine();
            Task task = factory.create(desc, start, end, pr);
            manager.addTask(task);
            System.out.println("Task added successfully. No conflicts.\n");
        } 
        catch (IllegalArgumentException ex) 
        {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    private void removeTask(Scanner sc) 
    {
        if (manager.getAllTasksSorted().isEmpty()) 
        {
            System.out.println("No tasks scheduled for the day.\n");
            return;
        }
        try 
        {
            System.out.print("Description to remove: "); String desc = sc.nextLine();
            manager.removeTask(desc);
            System.out.println("Task removed successfully.\n");
        } 
        catch (IllegalArgumentException ex) 
        {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    private void viewTasks() 
    {
        List<Task> tasks = manager.getAllTasksSorted();
        if (tasks.isEmpty()) 
        {
            System.out.println("No tasks scheduled for the day.\n");
        } 
        else 
        {
            for (Task t : tasks) 
            {
                System.out.println(formatTask(t));
            }
            System.out.println();
        }
    }

    private void editTask(Scanner sc) 
    {
        if (manager.getAllTasksSorted().isEmpty()) 
        {
            System.out.println("No tasks scheduled for the day.\n");
            return;
        }
        try 
        {
            System.out.print("Existing description to edit: "); String oldDesc = sc.nextLine();
            Task existing = manager.getTaskByDescription(oldDesc);
            if (existing == null) 
            {
                System.out.println("Error: Task not found.\n");
                return;
            }
            boolean keepCompleted = false;
            if (existing.isCompleted()) 
            {
                System.out.print("This task is currently completed. Keep it completed after edit? (y/n): ");
                String ans = sc.nextLine();
                keepCompleted = ans != null && (ans.equalsIgnoreCase("y") || ans.equalsIgnoreCase("yes"));
            }

            System.out.print("New Description: "); String desc = sc.nextLine();
            System.out.print("New Start (HH:mm): "); String start = sc.nextLine();
            System.out.print("New End (HH:mm): "); String end = sc.nextLine();
            System.out.print("New Priority (High/Medium/Low): "); String pr = sc.nextLine();
            Task newTask = factory.create(desc, start, end, pr);
            manager.editTask(oldDesc, newTask);
            if (keepCompleted) 
            {
                manager.completeTask(newTask.getDescription());
            }
            System.out.println("Task edited successfully.\n");
        } 
        catch (IllegalArgumentException ex) 
        {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    private void completeTask(Scanner sc) 
    {
        if (manager.getAllTasksSorted().isEmpty()) 
        {
            System.out.println("No tasks scheduled for the day.\n");
            return;
        }
        if (!manager.hasActiveTasks()) {
            System.out.println("No active tasks available. All tasks are completed.\n");
            return;
        }
        try 
        {
            System.out.print("Description to mark completed: "); String desc = sc.nextLine();
            manager.completeTask(desc);
            System.out.println("Task marked as completed.\n");
        } 
        catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage() + "\n");
        }
    }

    private void viewByPriority(Scanner sc) 
    {
        List<Task> tasks = manager.getAllTasksSortedByPriority();
        if (tasks.isEmpty()) 
        {
            System.out.println("No tasks scheduled for the day.\n");
            return;
        }
        for (Task t : tasks) 
        {
            System.out.println(formatTask(t));
        }
        System.out.println();
    }

    private static String formatTask(Task t)
    {
        String pr = t.getPriority().toString().charAt(0) + t.getPriority().toString().substring(1).toLowerCase();
        String status = t.isCompleted() ? " (Completed)" : "";
        return String.format("%s - %s: %s [%s]%s", t.getStart(), t.getEnd(), t.getDescription(), pr, status);
    }
}
