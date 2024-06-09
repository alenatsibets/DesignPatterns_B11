package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.ManagementTask;
import edu.pattern.management.entity.Task;

public class ManagementTaskHandler extends AbstractTaskHandler {
    public ManagementTaskHandler(TaskHandler successor) {
        super(successor);
    }

    @Override
    protected boolean canHandle(Task task) {
        return task instanceof ManagementTask;
    }

    @Override
    protected void processTask(Task task) {
        logger.info("ManagementTaskHandler processTask" );
        System.out.println("ManagementTaskHandler:");
        task.process();
    }
}