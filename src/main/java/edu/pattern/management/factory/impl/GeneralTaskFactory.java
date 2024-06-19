package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.Task;
import edu.pattern.management.factory.TaskFactory;

/**
 * GeneralTaskFactory class implements TaskFactory interface.
 */
public class GeneralTaskFactory extends TaskFactory {
    /**
     * Method for creating a new Task.
     *
     * @return new Task depending on its type
     */
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        if (description.startsWith("BugTask")) {
            TaskFactory factory = new BugTaskFactory();
            return factory.createTask(name, deadline, personName, description);
        } else if (description.startsWith("FeatureTask")) {
            TaskFactory factory = new FeatureTaskFactory();
            return factory.createTask(name, deadline, personName, description);
        } else if (description.startsWith("ManagementTask")) {
            TaskFactory factory = new ManagementTaskFactory();
            return factory.createTask(name, deadline, personName, description);
        } else if (description.startsWith("CompositeTask")) {
            TaskFactory factory = new CompositeTaskFactory();
            return factory.createTask(name, deadline, personName, description);
        } else {
            throw new IllegalArgumentException("Unknown task type in description");
        }
    }
}
