import edu.pattern.management.chain.impl.BugTaskHandler;
import edu.pattern.management.chain.impl.CompositeTaskHandler;
import edu.pattern.management.chain.impl.FeatureTaskHandler;
import edu.pattern.management.chain.impl.ManagementTaskHandler;
import edu.pattern.management.entity.CompositeTask;
import edu.pattern.management.entity.Task;
import edu.pattern.management.entity.TaskStatus;
import edu.pattern.management.factory.TaskFactory;
import edu.pattern.management.factory.impl.BugTaskFactory;
import edu.pattern.management.factory.impl.CompositeTaskFactory;
import edu.pattern.management.factory.impl.FeatureTaskFactory;
import edu.pattern.management.factory.impl.ManagementTaskFactory;
import edu.pattern.management.strategy.Context;
import edu.pattern.management.strategy.impl.DefaultStrategy;
import edu.pattern.management.strategy.impl.TwiceDebugStrategy;
import edu.pattern.management.strategy.impl.UsualStrategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests for a project.
 */
public class B11Test {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    //Status checks
    @Test
    void checkStatusOfCteatedTask() {
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");

        assertEquals(bugTask.status, TaskStatus.TO_DO);
    }
    @Test
    void checkStatusOfProcessedTask() {
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();

        assertEquals(bugTask.status, TaskStatus.DONE);
    }
    @Test
    void checkStatusOfTaskInProcess() {
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.setStatus(TaskStatus.IN_PROCESS);

        assertEquals(bugTask.status, TaskStatus.IN_PROCESS);
    }

    //Task processing output
    @Test
    void checkBugTaskOutput() {
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();

        assertTrue(outContent.toString().contains("BugTask is processed... "));
    }
    @Test
    void checkFeatureTaskOutput() {
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        featureTask.process();

        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
    }
    @Test
    void checkManagementTaskOutput() {
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        managementTask.process();

        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }
    @Test
    void checkCompositeTaskOutput() {
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
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
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        bugTask.process();
        bugTask.process();

        assertTrue(outContent.toString().contains("BugTask is completed."));
    }
    @Test
    void checkFeatureTaskCompletedOutput() {
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        featureTask.process();
        featureTask.process();

        assertTrue(outContent.toString().contains("FeatureTask is completed."));
    }
    @Test
    void checkManagementTaskCompletedOutput() {
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        managementTask.process();
        managementTask.process();

        assertTrue(outContent.toString().contains("ManagementTask is completed."));
    }
    @Test
    void checkCompositeTaskCompletedOutput() {
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
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
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        BugTaskHandler bugTaskHandler = new BugTaskHandler(null);
        bugTaskHandler.handleTask(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
    }
    @Test
    void checkFeatureTaskHandlerOutput() {
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        FeatureTaskHandler featureTaskHandler = new FeatureTaskHandler(null);
        featureTaskHandler.handleTask(featureTask);

        assertTrue(outContent.toString().contains("FeatureTaskHandler"));
    }
    @Test
    void checkManagementTaskHandlerOutput() {
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        ManagementTaskHandler managementTaskHandler = new ManagementTaskHandler(null);
        managementTaskHandler.handleTask(managementTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
    }
    @Test
    void checkCompositeTaskHandlerOutput() {
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
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
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
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
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(managementTask);

        assertTrue(outContent.toString().contains("ManagementTaskHandler"));
        assertTrue(outContent.toString().contains("ManagementTask is processed... "));
    }
    @Test
    void checkDefaultStrategyBugOutput() {
        TaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
        assertTrue(outContent.toString().contains("BugTask is processed... "));
    }
    @Test
    void checkDefaultStrategyFeatureOutput() {
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(featureTask);

        assertTrue(outContent.toString().contains("FeatureTaskHandler"));
        assertTrue(outContent.toString().contains("FeatureTask is processed... "));
    }
    @Test
    void checkDefaultStrategyCompositeOutput() {
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        TaskFactory featureFactory = new FeatureTaskFactory();
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
                "CompositeTask: Create and debug a secure authentication system for user login.");
        compositeTask.addTask(managementTask);
        compositeTask.addTask(featureTask);
        Context defualtContext = new Context(new DefaultStrategy());
        defualtContext.executeStrategy(compositeTask);

        assertTrue(outContent.toString().contains("CompositeTaskHandler"));
    }
    @Test
    void checkUsualStrategyOutput() {
        CompositeTaskFactory compositeTaskFactory = new CompositeTaskFactory();
        ManagementTaskFactory managementTaskFactory = new ManagementTaskFactory();
        BugTaskFactory bugFactory = new BugTaskFactory();
        FeatureTaskFactory featureFactory = new FeatureTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Task managementTask = managementTaskFactory.createTask("Plan team meeting", "2024-05-30", "Charlie Brown",
                "ManagementTask: Organize agenda and schedule for the team meeting.");
        Task featureTask = featureFactory.createTask("Implement user authentication", "2024-05-25", "Alice Johnson",
                "FeatureTask: Create a secure authentication system for user login.");
        CompositeTask compositeTask = (CompositeTask) compositeTaskFactory.createTask("User authentication", "2024-06-02", "Alice Johnson",
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
        BugTaskFactory bugFactory = new BugTaskFactory();
        Task bugTask = bugFactory.createTask("Fix database connection issue", "2024-06-02", "Bob Smith",
                "BugTask: Investigate and resolve the database connection problem affecting the application.");
        Context usualContext = new Context(new TwiceDebugStrategy());
        usualContext.executeStrategy(bugTask);

        assertTrue(outContent.toString().contains("BugTaskHandler"));
        assertTrue(outContent.toString().contains("BugTask is processed... "));
        assertTrue(outContent.toString().contains("BugTask is completed."));
    }
}
