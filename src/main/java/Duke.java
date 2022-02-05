import project.gennie.Greeting;
import project.gennie.addTasks;

import java.util.Arrays;
import java.util.Scanner;

public class Duke
{
    public static void main(String[] args)
    {
        Greeting greetingObject = new Greeting();
        greetingObject.printGreeting();
        addTasks addTasksObject = new addTasks();
        addTasksObject.addTasks();
    }
}
