package edu.pattern.management.factory;

import edu.pattern.management.entity.Task;

public interface TaskFactory {
    Task createTask(String name, String deadline, String personName, String description);
}