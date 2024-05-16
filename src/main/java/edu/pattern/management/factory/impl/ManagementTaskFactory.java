package edu.pattern.management.factory.impl;

import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.ManagementTask;
import edu.pattern.management.factory.TaskFactory;

public class ManagementTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        return new ManagementTask(name, deadline, personName, description);
    }
}