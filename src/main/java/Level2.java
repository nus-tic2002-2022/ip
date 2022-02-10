import java.util.Scanner;
import java.util.*;

public class Level2 {
    public Scanner in = new Scanner(System.in);
    String input;
    ArrayList<Task> taskArr = new ArrayList<Task>(100);

    public void query(){
        while(true) {
            input = in.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                exit();
                break;
            } else {
                System.out.println(input);
            }
            switch(input) {
                case "list":
                    list();
                    break;
                case "bye":
                    exit();
                    break;
                default:
                    taskArr.add(new Task(input));
                    System.out.println("added: " + input);
            }
        }
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    public void list(){
        for(int i=0;i<taskArr.size();i++){
            System.out.println((i+1) + ". " + taskArr.get(i).getDescription());
        }
    }
}
