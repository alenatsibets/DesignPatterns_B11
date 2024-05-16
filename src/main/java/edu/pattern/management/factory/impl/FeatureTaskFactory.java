package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.FeatureTask;

public class FeatureTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        return new FeatureTask(name, deadline, personName, description);
    }
}