package tasks;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TaskListTest {
    private static TaskList taskList;

    @BeforeAll
    static public void taskListTest() {
        Task testTask = new Todo("test");
        taskList = new TaskList();
        taskList.addTask(testTask);
    }

    @Test
    public void getNumberOfTask_withOneTask_success() {
        assertEquals(1, taskList.getNumberOfTask());
    }

    @Test
    public void deleteTask_validIndex_success() {
        taskList.deleteTask(0);
        assertEquals(0,taskList.getNumberOfTask());
    }

    @Test
    public void deleteTask_invalidIndex_throwsIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,()-> taskList.deleteTask(69));
    }

    @Test
    public void getTask_validIndex_throwsIndexOutOfBounds() {
        assertEquals("[T][ ] test",taskList.get(0).toString());
    }

    @Test
    public void getTask_invalidIndex_throwsIndexOutOfBounds() {
        assertThrows(IndexOutOfBoundsException.class,()-> taskList.get(69));
    }
}
