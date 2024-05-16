package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.Task;

public abstract class AbstractTaskHandler implements TaskHandler {
    private final TaskHandler successor;

    public AbstractTaskHandler(TaskHandler successor) {
        this.successor = successor;
    }

    protected boolean canHandle(Task task) {
        return false;
    }

    @Override
    public void handleTask(Task task) {
        if (canHandle(task)) {
            processTask(task);
        }
        if (successor != null) {
            successor.handleTask(task);
        }
    }

    protected abstract void processTask(Task task);
}