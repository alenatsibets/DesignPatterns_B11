package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.BugTask;
import edu.pattern.management.entity.Task;

/**
 * BugTaskHandler extends AbstractTaskHandler which implements TaskHandler interface.
 * It is used for handling a BugTask.
 */
public class BugTaskHandler extends AbstractTaskHandler {
    /**
     * Constructor of BugTaskHandler, setting the next Handler in the chain.
     * @param successor next Handler
     */
    public BugTaskHandler(TaskHandler successor) {
        super(successor);
    }

    /**
     * Method for checking whether the Task is BugTask.
     * @param task task to handle
     * @return boolean value
     */
    @Override
    protected boolean canHandle(Task task) {
        return task instanceof BugTask;
    }

    /**
     * Method for processing the BugTask.
     * @param task task to handle
     */
    @Override
    protected void processTask(Task task) {
        logger.info("BugTaskHandler processTask" );
        System.out.println("BugTaskHandler:");
        task.process();
    }
}