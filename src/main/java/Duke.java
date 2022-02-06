import project.gennie.Greeting;
import project.gennie.addTasks;
import project.gennie.markTasks;

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
