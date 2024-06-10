package edu.pattern.management.chain;

import edu.pattern.management.entity.Task;

/**
 * TaskFactory interface. It declares a handleTask method.
 */
public interface TaskHandler {
    /**
     * Method for handling a specific Task.
     * @param task task to handle
     */
    void handleTask(Task task);
}