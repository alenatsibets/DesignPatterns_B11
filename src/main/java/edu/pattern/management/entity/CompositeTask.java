package edu.pattern.management.entity;

import java.util.LinkedList;

import static edu.pattern.management.entity.TaskStatus.DONE;
import static edu.pattern.management.entity.TaskStatus.IN_PROCESS;

/**
 * CompositeTask entity is a realisation of a composite design pattern.
 * This class extends Task abstract class and implements process() method.
 * It also has some own methods.
 */
public class CompositeTask extends Task {
    /**
     * List of Tasks related to one CompositeTask.
     */
    private LinkedList<Task> tasks = new LinkedList<>();

    /**
     * Constructor method for creating a CompositeTask.
     * @param name
     * @param deadline
     * @param personName
     * @param description
     */
    public CompositeTask(String name, String deadline, String personName, String description) {
        super(name, deadline, personName, description);
    }

    /**
     * Method for adding a Task to the CompositeTask list.
     * @param task Task to implement in a queue
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Method for removing the Task from the CompositeTask list.
     * @param task what Task to remove
     */
    public void removeTask(Task task) {
        tasks.remove(task);
    }

    /**
     * Method for removing the first Task from the CompositeTask list.
     */
    public Task removeFirstTask() {
        return tasks.remove();
    }

    /**
     * Method for getting the first Task from the CompositeTask list.
     * @return the first Task in a list
     */
    public Task getFirstTask() {
        return tasks.getFirst();
    }

    /**
     * Method for getting the CompositeTask list.
     * @return list of Tasks
     */
    public LinkedList<Task> getTasks() {
        return tasks;
    }

    /**
     * Method for setting list of Tasks as a CompositeTask list.
     * @param tasks list of Tasks to set
     */
    public void setTasks(LinkedList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method for checking whether all subtasks are completed.
     * @return boolean value
     */
    private boolean allSubTasksCompleted() {
        for (Task task : tasks) {
            if (!task.isCompleted()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Method for changing the CompositeTask (and all subtasks) status to DONE. Indicate what Task is processed.
     */
    @Override
    public void process() {
        switch (this.status) {
            case TO_DO:
                this.setStatus(IN_PROCESS);
            case IN_PROCESS:
                System.out.println("CompositeTask is processed... ");
                for (Task task : tasks) {
                    task.process();
                }
                this.finish();
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