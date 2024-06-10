package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.FeatureTask;

/**
 * FeatureTaskFactory class implements TaskFactory interface.
 */
public class FeatureTaskFactory implements TaskFactory {
    /**
     * Method for creating a new FeatureTask.
     * @return new FeatureTask
     */
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating FeatureTask");
        return new FeatureTask(name, deadline, personName, description);
    }
}