package edu.pattern.management.entity;

import java.util.LinkedList;

import static edu.pattern.management.entity.TaskStatus.DONE;
import static edu.pattern.management.entity.TaskStatus.IN_PROCESS;

public class CompositeTask extends Task {
    private LinkedList<Task> tasks = new LinkedList<>();

    public CompositeTask(String name, String deadline, String personName, String description) {
        super(name, deadline, personName, description);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
    }

    public Task removeFirstTask() {
        return tasks.remove();
    }

    public Task getFirstTask() {
        return tasks.getFirst();
    }

    public LinkedList<Task> getTasks() {
        return tasks;
    }

    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    private boolean allSubTasksCompleted() {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void process() {
        switch (this.status) {
            case TO_DO:
                this.setStatus(IN_PROCESS);
            case IN_PROCESS:
                System.out.println("CompositeTask: --process");
                for (Task task : tasks) {
                    task.process();
                }
                break;
            case DONE:
                System.out.println("This CompositeTask is completed.");
                break;
            default:
                System.out.println("Unknown state");
                break;
        }
    }

    @Override
    public void finish() {
        if (!this.isCompleted() && allSubTasksCompleted()) {
            this.setStatus(DONE);
        }
        System.out.println(this.status);
    }
}