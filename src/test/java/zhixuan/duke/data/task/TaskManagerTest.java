package zhixuan.duke.data.task;

import org.junit.jupiter.api.*;
import zhixuan.duke.common.EnumTask;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class TaskManagerTest {

    private final static PrintStream out = System.out;
    private final static ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @BeforeAll
    public static void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public static void cleanUp() {
        System.setOut(out);
    }

    @Test
    void addToTaskList_validTask_success() {
        // Add todo
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, EnumTask.TODO, false, "Test");
        String expectedOutput = "Roger. I will add this to your list: \n" +
                "[T][ ] Test" + "\n" +
                "You have 1 tasks in your list." + "\n" +
                "---------------------------------------\r\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
        // Add event
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, EnumTask.EVENT, false, "Finals /at 2022-04-25 09:00");
        String expectedOutput2 = "Roger. I will add this to your list: \n" +
                "[E][ ] Finals (at: 25 Apr 2022 09:00)" + "\n" +
                "You have 2 tasks in your list." + "\n"+
                "---------------------------------------\r\n";
        String actualOutput2 = outContent.toString();
        assertEquals(expectedOutput2, actualOutput2);
        // Add deadline
        outContent.reset();
        TaskManager.getInstance().addToTaskList(false, EnumTask.DEADLINE, false, "Bedtime /by 2022-01-01 23:59");
        String expectedOutput3 = "Roger. I will add this to your list: \n" +
                "[D][ ] Bedtime (by: 01 Jan 2022 23:59)" + "\n" +
                "You have 3 tasks in your list." + "\n" +
                "---------------------------------------\r\n";
        String actualOutput3 = outContent.toString();
        assertEquals(expectedOutput3, actualOutput3);
    }

    @Test
    void deleteTaskList_validTask_success() {
        // delete 2
        outContent.reset();
        TaskManager.getInstance().deleteTask(2);
        String expectedOutput = "Deleted this task: \n" +
                "[E][ ] Finals (at: 25 Apr 2022 09:00)" + "\n" +
                "You have 2 tasks in your list." + "\n" +
                "---------------------------------------\r\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void listTaskList_validList_success() {
        // List
        outContent.reset();
        TaskManager.getInstance().listTask();
        String expectedOutput = "Here are the tasks in your list: \n" +
                "1.[T][ ] Test" + "\n" +
                "2.[D][ ] Bedtime (by: 01 Jan 2022 23:59)" + "\n" +
                "---------------------------------------\r\n";
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    void markTaskList_validTask_success() {
        // mark 1
        outContent.reset();
        TaskManager.getInstance().markTask("mark", 1);
        String expectedOutput = "Nice! I've marked this task as done: \n" +
                "[T][X] Test" + "\n" +
                "---------------------------------------\r\n";;
        String actualOutput = outContent.toString();
        assertEquals(expectedOutput, actualOutput);
        // mark 1 - Already marked
        outContent.reset();
        TaskManager.getInstance().markTask("mark", 1);
        String expectedOutput2 = "Task is already marked as done: \n" +
                "[T][X] Test" + "\n" +
                "---------------------------------------\r\n";;
        String actualOutput2 = outContent.toString();
        assertEquals(expectedOutput2, actualOutput2);
        // unmark 1
        outContent.reset();
        TaskManager.getInstance().markTask("unmark", 1);
        String expectedOutput3 = "OK, I've marked this task as not done yet: \n" +
                "[T][ ] Test" + "\n" +
                "---------------------------------------\r\n";;
        String actualOutput3 = outContent.toString();
        assertEquals(expectedOutput3, actualOutput3);
        // unmark 1 - Already unmarked
        outContent.reset();
        TaskManager.getInstance().markTask("unmark", 1);
        String expectedOutput4 = "Task is already marked as not done: \n" +
                "[T][ ] Test" + "\n" +
                "---------------------------------------\r\n";;
        String actualOutput4 = outContent.toString();
        assertEquals(expectedOutput4, actualOutput4);
    }
}

