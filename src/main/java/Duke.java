import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;

    public static void main(String[] args) {
        System.out.println("-______________________-||");
        System.out.println("Hello! I am the most superior AI~~ Friday");
        System.out.println("What can I do for you?");
        Scanner in = new Scanner(System.in);
        String echo = "";
        echo = in.nextLine();

        while(!echo.equals("bye")){
            if(echo.equals("list")){
                System.out.println("=========================================" );
                printTask();
                echo = in.nextLine();
                continue;
            }
            System.out.println("=========================================" );
            addTask(echo);
            System.out.println("added: " + echo);
            echo = in.nextLine();
        }
        System.out.println("=========================================" );
        System.out.println("Bye. Hope to see you again! Love you 3000 <3");
    }

    public static void addTask(String desc){
        tasks[taskCount] = new Task(desc);
        taskCount++;
    }

    public static void printTask(){
        for(int i = 0; i < taskCount; i ++){
            System.out.print((i + 1) + ". ");
            tasks[i].printTask();
        }
    }
}
