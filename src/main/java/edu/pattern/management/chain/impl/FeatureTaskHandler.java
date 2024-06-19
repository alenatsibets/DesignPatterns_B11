package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.FeatureTask;
import edu.pattern.management.entity.Task;

/**
 * FeatureTaskHandler extends AbstractTaskHandler which implements TaskHandler interface.
 * It is used for handling a FeatureTask.
 */
public class FeatureTaskHandler extends AbstractTaskHandler {
    /**
     * Constructor of FeatureTaskHandler, setting the next Handler in the chain.
     *
     * @param successor next Handler
     */
    public FeatureTaskHandler(TaskHandler successor) {
        super(successor);
    }

    /**
     * Method for checking whether the Task is FeatureTask.
     *
     * @param task task to handle
     * @return boolean value
     */
    @Override
    protected boolean canHandle(Task task) {
        return task instanceof FeatureTask;
    }

    /**
     * Method for processing the FeatureTask.
     *
     * @param task task to handle
     */
    @Override
    protected void processTask(Task task) {
        logger.info("FeatureTaskHandler processTask");
        System.out.println("FeatureTaskHandler:");
        task.process();
    }
}