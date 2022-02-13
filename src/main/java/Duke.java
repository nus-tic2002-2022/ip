import java.util.Scanner;

public class Duke {
    private TaskHandler TaskHandler;
    private TaskList taskList;
    private Task task;
    public static void main(String[] args) {
        new Duke().start();
    }

    public void start() {
        TaskHandler = new TaskHandler();
        taskList = new TaskList();
        System.out.println(TaskHandler.hello());
        boolean isExit = false;
        while (!isExit) {
            try {
                String text = TaskHandler.getCommand();
                if (text.equals("bye")) {
                    System.out.println(TaskHandler.bye());
                    isExit = true;
                } else if (text.equals("list")) {
                    System.out.println(TaskHandler.showList());
                } else if(text.startsWith("mark")){
                    System.out.println(TaskHandler.mark(text));
                } else if(text.startsWith("unmark")){
                    System.out.println(TaskHandler.unmark(text));
                } else if(text.startsWith("todo")){
                    System.out.println(TaskHandler.todo(text));
                } else if(text.startsWith("deadline")){
                    System.out.println(TaskHandler.deadline(text));
                } else if(text.startsWith("event")){
                    System.out.println(TaskHandler.event(text));
                } else{
                    task = new Task(text);
                    taskList.add(task);
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}