import java.util.*;

public class Duke {
    private static final ArrayList<Task> task = new ArrayList<Task>();
    private static boolean iDuke = true;
    private static int count=0;
    private static final String ln = "____________________________________________________________";

    public static void Greet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println(ln);
        System.out.println("I'm Duke" +System.lineSeparator()
                + "What can I do for you?");
        System.out.println(ln);
    }

    public static void Exit(){
        System.out.println("Goodbye，Hope to see you again soon!");
        iDuke = false;
        System.exit(0);
    }

    public static void printAdded(String content) {
        int numTask = count+1;
        System.out.println(ln + System.lineSeparator()
                + "Got it. I've added this task: ");
        System.out.println(content);
        System.out.println("Now you have " + numTask
                + " tasks in the list.");
        System.out.println(ln);
    }

    public static void printList(){
        int num=1;
        System.out.println(ln +System.lineSeparator()
                + "Here are the tasks in your list:");
        for (int i = 0; i<count; i++) {
            System.out.println(num + ".[" + task.get(i).getStatusIcon()
                    +"] "+ task.get(i).getDescription());
            num++;
        }
        System.out.println(ln);
    }
    public static void toDo(String enter) throws Exception{
        String[] _enter = enter.split(" ",2);
        String type=(_enter[0]).trim();
        try {
            task.add(count, new Task(_enter[1]));
            printAdded(task.get(count).toString());
            count++;
        }
        catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    public static void Done(String enter) throws Exception{
        String[] _enter = enter.split(" ",2);
        int taskNum = 0;
        try{
            taskNum = Integer.parseInt(_enter[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid number");
        }
        if(taskNum > count)
            System.out.println("Invalid task number");
        task.get(taskNum-1).markAsDone();
        System.out.println(ln+ System.lineSeparator() +
                "Nice! I've marked this task as done:\n"
                +"[" + task.get(taskNum-1).getStatusIcon()+"] "
                + task.get(taskNum-1).getDescription()
                +"\n"
                +ln);
    }
    public static void Event(String enter) throws Exception{
        String[] _enter = enter.split(" ",2);
        String type=(_enter[0]).trim();
        try {
            String[] dl = _enter[1].split("/");
            String[] at = (dl[1].split(" ", 2));
            task.add(count, new Event((dl[0]).trim(), at[1].trim()));
            printAdded(task.get(count).toString());
            count++;
        }
        catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    public static void Deadline(String enter) throws Exception{
        String[] _enter = enter.split(" ",2);
        String type=(_enter[0]).trim();
        try{
            String[] dl = _enter[1].split("/");
            String[] by = (dl[1].split(" ", 2));
            task.add(count, new Deadline((dl[0]).trim(), by[1].trim()));
            printAdded(task.get(count).toString());
            count++;}
        catch (RuntimeException e) {
            throw new MissDescException(type);
        }
    }

    private static void execute() throws Exception{
        Scanner in = new Scanner(System.in);
        String enter= (in.nextLine()).trim();
        String[] _enter = enter.split(" ",2);
        String type=(_enter[0]).trim();
        if(enter.length()==0) {
            throw new DukeException("Invalid input");
        }
        switch (type){
            case "bye":{
                Exit();
            } break;
            case"done":{
                Done(enter);
            }break;
            case"list":{
                printList();
            }break;
            case"deadline": {
                Deadline(enter);
            }break;
            case "event":{
                Event(enter);
            } break;
            case "todo":{
                toDo(enter);
            } break;
            default:{
                throw new DukeException("");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Greet();
        while (iDuke){
            try {
                execute();
            } catch (Exception e){
            }
        }
    }
}