package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * TaskHandler as an abstract class, realisation of Chain of responsibilities design pattern.
 * It implements TaskHandler interface and contains a Logger.
 */
public abstract class AbstractTaskHandler implements TaskHandler {
    /**
     * Logger (configuration is declared in log4j2.xml file)
     */
    static Logger logger = LogManager.getLogger();
    /**
     * The next Handler in the chain.
     */
    private final TaskHandler successor;

    /**
     * Constructor for making a chain.
     * @param successor next Handler
     */
    protected AbstractTaskHandler(TaskHandler successor) {
        this.successor = successor;
    }

    /**
     * Method for checking whether the Task type is compatible with the Handler task.
     * @param task task to handle
     * @return boolean value
     */
    protected boolean canHandle(Task task) {
        return false;
    }

    /**
     * Method for executing the chain. It uses processTask(Task task) method
     * and give this opportunity for the next Handler.
     * @param task task to handle
     */
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

    /**
     * Method for processing the Task.
     * @param task task to handle
     */
    protected abstract void processTask(Task task);
}