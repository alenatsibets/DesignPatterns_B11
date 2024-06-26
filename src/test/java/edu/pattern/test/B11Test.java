package edu.pattern.test;

import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.chain.impl.CompositeTaskHandler;
import edu.pattern.management.chain.impl.FeatureTaskHandler;
import edu.pattern.management.chain.impl.ManagementTaskHandler;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.TaskStatus;
import edu.pattern.management.exception.TaskException;
import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.factory.impl.*;
import edu.pattern.management.strategy.Context;
import edu.pattern.management.strategy.impl.DefaultStrategy;
import edu.pattern.management.strategy.impl.TwiceDebugStrategy;
import edu.pattern.management.strategy.impl.UsualStrategy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for a project.
 */
class B11Test {
    /**
     * Logger (configuration is declared in log4j2.xml file).
     */
    static Logger logger = LogManager.getLogger();
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    /**
     * Sets up the standard output stream to be redirected to a {@link ByteArrayOutputStream}
     * before each test. This allows capturing of the output for verification.
     */
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    /**
     * Restores the standard output stream to its original state after each test.
     */
    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    //Status checks
    @Test
    void checkStatusOfCreatedTask() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");

        assertEquals(TaskStatus.TO_DO, bugTask.status);
    }

    @Test
    void checkStatusOfProcessedTask() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();

        assertEquals(TaskStatus.DONE, bugTask.status);
    }

    @Test
    void checkStatusOfTaskInProcess() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.setStatus(TaskStatus.IN_PROCESS);

        assertEquals(TaskStatus.IN_PROCESS, bugTask.status);
    }

    //Task processing output
    @Test
    void checkBugTaskOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();

        assertTrue(outContent.toString().contains("BugTask is processed... "));
    }

    @Test
    void checkFeatureTaskOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task featureTask = factory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        featureTask.process();

        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
    }

    @Test
    void checkManagementTaskOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        managementTask.process();

        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }

    @Test
    void checkCompositeTaskOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) factory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        compositeTask.process();

        assertTrue(outContent.toString().contains("CompositeTask is processed... "));
        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }

    //Task complete output
    @Test
    void checkBugTaskCompletedOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();
        bugTask.process();

        assertTrue(outContent.toString().contains("BugTask is completed."));
    }

    @Test
    void checkFeatureTaskCompletedOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task featureTask = factory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        featureTask.process();
        featureTask.process();

        assertTrue(outContent.toString().contains("FeatureTask is completed."));
    }

    @Test
    void checkManagementTaskCompletedOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        managementTask.process();
        managementTask.process();

        assertTrue(outContent.toString().contains("ManagementTask is completed."));
    }

    @Test
    void checkCompositeTaskCompletedOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) factory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        compositeTask.process();
        compositeTask.process();

        assertTrue(outContent.toString().contains("CompositeTask is completed."));
    }

    //Task handlers output
    @Test
    void checkBugTaskHandlerOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        BugTaskHandler bugTaskHandler = new BugTaskHandler(null);
        bugTaskHandler.handleTask(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
    }

    @Test
    void checkFeatureTaskHandlerOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task featureTask = factory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        FeatureTaskHandler featureTaskHandler = new FeatureTaskHandler(null);
        featureTaskHandler.handleTask(featureTask);

        assertTrue(outContent.toString().contains("FeatureTaskHandler"));
    }

    @Test
    void checkManagementTaskHandlerOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        ManagementTaskHandler managementTaskHandler = new ManagementTaskHandler(null);
        managementTaskHandler.handleTask(managementTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
    }

    @Test
    void checkCompositeTaskHandlerOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) factory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        CompositeTaskHandler compositeTaskHandler = new CompositeTaskHandler(null);
        compositeTaskHandler.handleTask(compositeTask);

        assertTrue(outContent.toString().contains("CompositeTaskHandler"));
        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }

    @Test
    void checkManagementChainHandlerOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        ManagementTaskHandler managementTaskHandler = new ManagementTaskHandler(new ManagementTaskHandler(null));
        managementTaskHandler.handleTask(managementTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
        assertTrue(outContent.toString().contains("ManagementTask is completed."));
    }

    //Strategy checks
    @Test
    void checkDefaultStrategyManagementOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(managementTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }

    @Test
    void checkDefaultStrategyBugOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
        assertTrue(outContent.toString().contains("BugTask is processed... "));
    }

    @Test
    void checkDefaultStrategyFeatureOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task featureTask = factory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(featureTask);

        assertTrue(outContent.toString().contains("FeatureTaskHandler"));
        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
    }

    @Test
    void checkDefaultStrategyCompositeOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) factory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(compositeTask);

        assertTrue(outContent.toString().contains("CompositeTaskHandler"));
    }

    @Test
    void checkUsualStrategyOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Task managementTask = factory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        Task featureTask = factory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) factory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        compositeTask.addTask(bugTask);
        Context usualContext = new Context(new UsualStrategy());
        usualContext.executeStrategy(compositeTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
        assertTrue(outContent.toString().contains("FeatureTaskHandler"));
        assertTrue(outContent.toString().contains("BugTaskHandler"));
    }

    @Test
    void checkTwiceDebugStrategyOutput() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Context usualContext = new Context(new TwiceDebugStrategy());
        usualContext.executeStrategy(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
        assertTrue(outContent.toString().contains("BugTask is processed... "));
        assertTrue(outContent.toString().contains("BugTask is completed."));
    }

    //check reader functionality
    @Test
    void checkReader() {
        TaskFactory factory = new GeneralTaskFactory();
        Task bugTask = factory.createTask("Fix database connection issue", "2024-06-02", "Alice Johnson",
                "BugTask. Investigate and resolve the database connection problem affecting the application.");
        Task bugTask2 = null;
        try {
            bugTask2 = factory.createTask("task/bugTask.yaml");
        } catch (TaskException e) {
            logger.error("Cannot read the file", e);
        }

        assertEquals(bugTask, bugTask2);
    }

    //check TaskException
    @Test
    void checkException() {
        TaskFactory factory = new GeneralTaskFactory();

        assertThrows(TaskException.class, () -> {
            factory.createTask("");
        });
    }
}
