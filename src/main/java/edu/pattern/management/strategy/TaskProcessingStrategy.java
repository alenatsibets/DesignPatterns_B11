package edu.pattern.management.strategy;

import edu.pattern.management.entity.Task;

public interface TaskProcessingStrategy {
    void processTask(Task task);
}