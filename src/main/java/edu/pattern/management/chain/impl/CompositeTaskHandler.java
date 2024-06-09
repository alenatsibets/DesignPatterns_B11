package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;

public class CompositeTaskHandler extends AbstractTaskHandler {
    public CompositeTaskHandler(TaskHandler successor) {
        super(successor);
    }

    @Override
    protected boolean canHandle(Task task) {
        return task instanceof CompositeTask;
    }

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
