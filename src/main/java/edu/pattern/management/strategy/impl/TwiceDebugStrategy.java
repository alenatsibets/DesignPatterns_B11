package edu.pattern.management.strategy.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.entity.Task;
import edu.pattern.management.strategy.TaskProcessingStrategy;

/**
 * TwiceDebugStrategy class is a realisation of a Strategy design pattern. It is used for twice handling a BugTask.
 */
public class TwiceDebugStrategy implements TaskProcessingStrategy {
    /**
     * Method for twice handling the BudTask.
     * @param task BugTask to handle
     */
    @Override
    public void processTask(Task task) {
        TaskHandler taskHandler = new BugTaskHandler(new BugTaskHandler(null));
        taskHandler.handleTask(task);
    }
}