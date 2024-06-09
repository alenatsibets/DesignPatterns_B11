package edu.pattern.management;

import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.factory.impl.BugTaskFactory;
import edu.pattern.management.factory.impl.CompositeTaskFactory;
import edu.pattern.management.factory.impl.FeatureTaskFactory;
import edu.pattern.management.factory.impl.ManagementTaskFactory;
import edu.pattern.management.strategy.Context;
import edu.pattern.management.strategy.impl.DefaultStrategy;
import edu.pattern.management.strategy.impl.TwiceDebugStrategy;
import edu.pattern.management.strategy.impl.UsualStrategy;

/**
 * This class is created as a client to processed project functionality.
 */

public class Main {
    public static void main(String[] args) {
        TaskFactory bugFactory = new BugTaskFactory();
        TaskFactory featureFactory = new FeatureTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();

        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Task bugTask2 = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");

        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        compositeTask.addTask(bugTask);

        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(managementTask);
        defualtContext.executeStrategy(featureTask);
        defualtContext.executeStrategy(bugTask);
        defualtContext.executeStrategy(compositeTask);
        System.out.println();

        Context usualContext = new Context(new UsualStrategy());
        usualContext.executeStrategy(compositeTask);
        System.out.println();

        Context twiceDebugContext = new Context(new TwiceDebugStrategy());
        twiceDebugContext.executeStrategy(bugTask2);
    }
}