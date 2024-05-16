package edu.pattern.management.chain;

import edu.pattern.management.entity.Task;

public interface TaskHandler {
    void handleTask(Task task);
}