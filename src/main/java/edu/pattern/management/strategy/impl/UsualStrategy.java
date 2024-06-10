package edu.pattern.management.strategy.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.chain.impl.FeatureTaskHandler;
import edu.pattern.management.chain.impl.ManagementTaskHandler;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.strategy.TaskProcessingStrategy;

/**
 * UsualStrategy class is a realisation of a Strategy design pattern.
 * It is used for handling a ManagementTask, then handling a FeatureTask and then a BugTask (in a CompositeTask).
 */
public class UsualStrategy implements TaskProcessingStrategy {
    /**
     * Method for handling CompositeTask with a defined structure (UsualStrategy).
     * @param task CompositeTask to handle
     */
    @Override
    public void processTask(Task task) {
        TaskHandler taskHandler = new ManagementTaskHandler(new FeatureTaskHandler(new BugTaskHandler(null)));
        CompositeTask task1 = (CompositeTask) task;
        for (Task subTask : task1.getTasks()) {
            taskHandler.handleTask(subTask);
        }
    }
}