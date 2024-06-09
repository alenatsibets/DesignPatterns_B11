package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractTaskHandler implements TaskHandler {
    static Logger logger = LogManager.getLogger();
    private final TaskHandler successor;

    protected AbstractTaskHandler(TaskHandler successor) {
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
            logger.info("handleTask: successor = " + successor);
        }
    }

    protected abstract void processTask(Task task);
}