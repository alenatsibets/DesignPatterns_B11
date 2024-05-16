package edu.pattern.management.strategy;

import edu.pattern.management.entity.Task;

public class Context {
    private TaskProcessingStrategy strategy;

    public Context(TaskProcessingStrategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy(Task task) {
        strategy.processTask(task);
    }

    public void setStrategy(TaskProcessingStrategy strategy) {
        this.strategy = strategy;
    }
}