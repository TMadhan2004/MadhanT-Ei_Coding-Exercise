package ex2.observer;

import ex2.core.Task;

public class ConflictObserver implements ScheduleObserver 
{
    @Override
    public void onConflict(Task newTask, Task conflicting) 
    {
        System.out.println("[Observer] Conflict: '" + newTask.getDescription() + "' overlaps with '" + conflicting.getDescription() + "'.");
    }
}
