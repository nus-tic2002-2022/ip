package duke;
import java.io.IOException;

/**
 * author Me
 */
public class Duke {
    private TaskHandler TaskHandler; // similar like UI
    private TaskList taskList;
    private Task task;
    private Storage storage;

    /*public Duke(){
        TaskHandler = new TaskHandler();
        storage = new Storage("data/tasks.txt");
        try{
            taskList = new TaskList();
            storage.ReadFile();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }*/
    public Duke(String filePath) {
        TaskHandler = new TaskHandler();
        storage = new Storage(filePath);
        try{
            taskList = new TaskList();
            storage.ReadFile();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public Duke() {
        TaskHandler = new TaskHandler();
        try{
            taskList = new TaskList();
            storage.ReadFile();
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    /**
     *
     * @param args Main class start here
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").start();
    }

    /**
     * function navigation by input text
     */
    public void start() {
        System.out.println(TaskHandler.hello());
        boolean isExit = false;
        while (!isExit) {
            try {
                String text = TaskHandler.getCommand();
                /**
                 * add assert
                 */
                assert text.length() > 0;
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
                    if(text.contains("todo ") && text.length()>5){
                        System.out.println(TaskHandler.todo(text));
                    }else {
                        throw new ExceptionEmpty();
                    }

                } else if(text.startsWith("deadline")){
                    if(text.contains("deadline ") && text.length()>9){
                        System.out.println(TaskHandler.deadline(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                } else if(text.startsWith("event")){
                    if(text.contains("event ") && text.length()>6){
                        System.out.println(TaskHandler.event(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                } else if(text.startsWith("delete")){
                    if(text.contains("delete ") && text.length()>7){
                        System.out.println(TaskHandler.delete(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                }else if(text.startsWith("find")){
                    if(text.contains("find ") && text.length()>5){
                        System.out.println(TaskHandler.find(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                }else if(text.startsWith("show")){
                    if(text.contains("show")){
                        System.out.println(TaskHandler.show());
                    }else {
                        throw new ExceptionEmpty();
                    }
                }
                else{
                    if(!text.startsWith("todo") ||!text.startsWith("delete") ||!text.startsWith("deadline")||!text.startsWith("event")||!text.startsWith("mark")||!text.startsWith("unmark")||!text.startsWith("list")) {
                        throw new ExceptionErrorMessage();
                    }else{
                        task = new Task(text);
                        taskList.add(task);
                    }
                }
                storage.saveTasks();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}