package edu.pattern.management.strategy.impl;

import edu.pattern.management.chain.TaskHandler;
import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.entity.Task;
import edu.pattern.management.strategy.TaskProcessingStrategy;

public class TwiceDebugStrategy implements TaskProcessingStrategy {
    @Override
    public void processTask(Task task) {
        TaskHandler taskHandler = new BugTaskHandler(new BugTaskHandler(null));
        taskHandler.handleTask(task);
    }
}