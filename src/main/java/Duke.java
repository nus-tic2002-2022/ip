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
                    if(text.contains("todo ") && text.length()>5){
                        System.out.println(TaskHandler.todo(text));
                    }else {
                        throw new ExceptionEmpty();
                    }

                } else if(text.startsWith("deadline")){
                    if(text.contains("deadline ") && text.length()>9){
                        System.out.println(TaskHandler.todo(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                } else if(text.startsWith("event")){
                    if(text.contains("event ") && text.length()>6){
                        System.out.println(TaskHandler.todo(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                } else if(text.startsWith("delete")){
                    if(text.contains("delete ") && text.length()>7){
                        System.out.println(TaskHandler.delete(text));
                    }else {
                        throw new ExceptionEmpty();
                    }
                }else{
                    if(!text.startsWith("todo") ||!text.startsWith("delete") ||!text.startsWith("deadline")||!text.startsWith("event")||!text.startsWith("mark")||!text.startsWith("unmark")||!text.startsWith("list")) {
                        throw new ExceptionErrorMessage();
                    }else{
                        task = new Task(text);
                        taskList.add(task);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}