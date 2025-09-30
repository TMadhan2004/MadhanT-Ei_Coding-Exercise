public class LoggingObserver implements ScheduleObserver 
{
    private final Logger logger = Logger.getInstance();
    @Override public void onTaskAdded(Task task) 
    { 
        logger.info("Task added: " + task); 
    }
    @Override public void onTaskRemoved(Task task) 
    { 
        logger.info("Task removed: " + task.getDescription()); 
    }
    @Override public void onTaskCompleted(Task task)
    { 
        logger.info("Task completed: " + task.getDescription()); 
    }
    @Override public void onTaskEdited(Task oldTask, Task newTask) 
    { 
        logger.info("Task edited: '" + oldTask.getDescription() + "' -> '" + newTask + "'"); 
    }
}
