package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;

/**
 * CompositeTaskHandler extends AbstractTaskHandler which implements TaskHandler interface.
 * It is used for handling a CompositeTask.
 */
public class CompositeTaskHandler extends AbstractTaskHandler {
    /**
     * Constructor of CompositeTaskHandler, setting the next Handler in the chain.
     * @param successor next Handler
     */
    public CompositeTaskHandler(TaskHandler successor) {
        super(successor);
    }

    /**
     * Method for checking whether the Task is CompositeTask.
     * @param task task to handle
     * @return boolean value
     */
    @Override
    protected boolean canHandle(Task task) {
        return task instanceof CompositeTask;
    }

    /**
     * Method for processing the CompositeTask (processing all subtasks).
     * @param task task to handle
     */
    @Override
    protected void processTask(Task task) {
        logger.info("CompositeTaskHandler processTask" );
        System.out.println("CompositeTaskHandler:");
        CompositeTask task1 = (CompositeTask) task;
        for (Task subTask : task1.getTasks()) {
            subTask.process();
        }
    }
}
