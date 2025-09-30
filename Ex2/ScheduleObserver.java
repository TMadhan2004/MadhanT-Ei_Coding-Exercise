public interface ScheduleObserver 
{
    default void onTaskAdded(Task task) {}
    default void onTaskRemoved(Task task) {}
    default void onTaskCompleted(Task task) {}
    default void onTaskEdited(Task oldTask, Task newTask) {}
    default void onConflict(Task newTask, Task conflicting) {}
}
