package edu.pattern.management.factory.impl;

import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.FeatureTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FeatureTaskFactory implements TaskFactory {
    @Override
    public Task createTask(String name, String deadline, String personName, String description) {
        logger.info("Creating FeatureTask");
        return new FeatureTask(name, deadline, personName, description);
    }
}