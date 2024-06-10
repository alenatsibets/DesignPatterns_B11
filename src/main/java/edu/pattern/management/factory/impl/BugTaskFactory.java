package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.BugTask;

/**
 * BugTaskFactory class implements TaskFactory interface.
 */
public class BugTaskFactory implements TaskFactory {
    /**
     * BugTaskFactory for creating a new BugTask.
     * @param name
     * @param deadline
     * @param personName
     * @param description
     * @return new BugTask
     */
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating BugTask");
        return new BugTask(name, deadline, personName, description);
    }
}