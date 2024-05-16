package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.BugTask;

public class BugTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        return new BugTask(name, deadline, personName, description);
    }
}