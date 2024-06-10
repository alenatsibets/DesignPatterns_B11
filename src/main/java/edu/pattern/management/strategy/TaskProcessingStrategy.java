package edu.pattern.management.strategy;

import edu.pattern.management.entity.Task;

/**
 * TaskProcessingStrategy interface. It declares a processTask method.
 */
public interface TaskProcessingStrategy {
    void processTask(Task task);
}