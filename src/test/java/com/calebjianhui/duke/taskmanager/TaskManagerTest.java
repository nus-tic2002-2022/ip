package com.calebjianhui.duke.taskmanager;

import com.calebjianhui.duke.enums.TaskType;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class TaskManagerTest {
    private final static PrintStream standardOut = System.out;
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void cleanUp() {
        System.setOut(standardOut);
    }

    @Test
    void decodeTypeAlias_validAlias_success() {
        // Check the different type of alias
        // - ToDos
        assertEquals(TaskType.TODO, TaskManager.getInstance().decodeTypeAlias("T"));
        // - Event
        assertEquals(TaskType.EVENT, TaskManager.getInstance().decodeTypeAlias("E"));
        // - Deadline
        assertEquals(TaskType.DEADLINE, TaskManager.getInstance().decodeTypeAlias("D"));
        // - Fixed Duration Task
        assertEquals(TaskType.FIXED_DURATION, TaskManager.getInstance().decodeTypeAlias("F"));
    }

    @Test
    void decodeTypeAlias_invalidAlias_fail() {
        // - An invalid alias
        AssertionError error = assertThrows(AssertionError.class, () -> TaskManager.getInstance().decodeTypeAlias("G"));
        assertEquals("Invalid TaskType received.", error.getMessage());
        // - Same as [T] in ToDos but small caps
        AssertionError error2 = assertThrows(AssertionError.class, () -> TaskManager.getInstance().decodeTypeAlias("t"));
        assertEquals("Invalid TaskType received.", error2.getMessage());
    }

    @Test
    void addToTaskList_validTask_success() {
        // Test 1: Add ToDo
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.TODO, false, "Relax");
        String expectedOutput = "____________________________________________________________\n" +
                "~    Roger. I will add this to your list:\n" +
                "~        [T][ ] Relax\n" +
                "~    You currently have 1 task in your list.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
        // Test 2: Add Event
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.EVENT, false, "Exam /at 13/04/2022 12pm");
        expectedOutput = "____________________________________________________________\n" +
                "~    Roger. I will add this to your list:\n" +
                "~        [E][ ] Exam (on: 13/04/2022 12pm)\n" +
                "~    You currently have 2 task in your list.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
        // Test 3: Add Deadline
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.DEADLINE, false, "Submit Report /by 17/04/2022 23:59");
        expectedOutput = "____________________________________________________________\n" +
                "~    Roger. I will add this to your list:\n" +
                "~        [D][ ] Submit Report (by: 17/04/2022 23:59)\n" +
                "~    You currently have 3 task in your list.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
        // Test 4: Add FixedDurationTask
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.FIXED_DURATION, false, "Intensive studying /needs 2hour");
        expectedOutput = "____________________________________________________________\n" +
                "~    Roger. I will add this to your list:\n" +
                "~        [F][ ] Intensive studying (needs: 2hour)\n" +
                "~    You currently have 4 task in your list.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
    }

    @Test
    void addToTaskList_noTaskDuration_fail() {
        // Test 1: Fixed Duration require duration
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.FIXED_DURATION, false, "Study");
        String expectedOutput = "____________________________________________________________\n" +
                "~    Please include the duration for your fixed duration task.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
        // Test 2: Deadline require date
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.DEADLINE, false, "Study");
        expectedOutput = "____________________________________________________________\n" +
                "~    Please include the deadline for your task.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
        // Test 3: Event require date
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, TaskType.EVENT, false, "Study");
        expectedOutput = "____________________________________________________________\n" +
                "~    Please include the event date.\n" +
                "------------------------------------------------------------\n";
        assertEquals(expectedOutput, outContent.toString());
    }

}