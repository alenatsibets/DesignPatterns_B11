package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.factory.TaskFactory;

/**
 * CompositeTaskFactory class implements TaskFactory interface.
 */
public class CompositeTaskFactory extends TaskFactory {
    /**
     * Method for creating a new CompositeTask.
     *
     * @return new CompositeTask
     */
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating CompositeTask");
        return new CompositeTask(name, deadline, personName, description);
    }
}
