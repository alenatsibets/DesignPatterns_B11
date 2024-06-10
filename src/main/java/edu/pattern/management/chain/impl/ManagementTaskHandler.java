package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.ManagementTask;
import edu.pattern.management.entity.Task;

/**
 * ManagementTaskHandler extends AbstractTaskHandler which implements TaskHandler interface.
 * It is used for handling a ManagementTask.
 */
public class ManagementTaskHandler extends AbstractTaskHandler {
    /**
     * Constructor of ManagementTaskHandler, setting the next Handler in the chain.
     * @param successor next Handler
     */
    public ManagementTaskHandler(TaskHandler successor) {
        super(successor);
    }

    /**
     * Method for checking whether the Task is ManagementTask.
     * @param task task to handle
     * @return boolean value
     */
    @Override
    protected boolean canHandle(Task task) {
        return task instanceof ManagementTask;
    }

    /**
     * Method for processing the ManagementTask.
     * @param task task to handle
     */
    @Override
    protected void processTask(Task task) {
        logger.info("ManagementTaskHandler processTask" );
        System.out.println("ManagementTaskHandler:");
        task.process();
    }
}