package edu.pattern.management.chain.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.entity.FeatureTask;
import edu.pattern.management.entity.Task;

public class FeatureTaskHandler extends AbstractTaskHandler {
    public FeatureTaskHandler(TaskHandler successor) {
        super(successor);
    }

    @Override
    protected boolean canHandle(Task task) {
        return task instanceof FeatureTask;
    }

    @Override
    protected void processTask(Task task) {
        logger.info("FeatureTaskHandler processTask" );
        System.out.println("FeatureTaskHandler:");
        task.process();
    }
}