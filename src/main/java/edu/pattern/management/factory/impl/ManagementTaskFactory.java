package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.ManagementTask;
import edu.pattern.management.factory.TaskFactory;

/**
 * ManagementTaskFactory class implements TaskFactory interface.
 */
public class ManagementTaskFactory implements TaskFactory {
    /**
     * Method for creating a new ManagementTask.
     * @param name
     * @param deadline
     * @param personName
     * @param description
     * @return new ManagementTask
     */
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating Management Task");
        return new ManagementTask(name, deadline, personName, description);
    }
}