package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.BugTask;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.factory.TaskFactory;

public class CompositeTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating CompositeTask");
        return new CompositeTask(name, deadline, personName, description);
    }
}
