package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.BugTask;
import edu.pattern.management.entity.Task;

public class BugTaskHandler extends AbstractTaskHandler {
    public BugTaskHandler(TaskHandler successor) {
        super(successor);
    }

    @Override
    protected boolean canHandle(Task task) {
        return task instanceof BugTask;
    }

    @Override
    protected void processTask(Task task) {
        logger.info("BugTaskHandler processTask" );
        System.out.println("BugTaskHandler:");
        task.process();
    }
}