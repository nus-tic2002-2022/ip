import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class Level3 {
    public Scanner in = new Scanner(System.in);
    String input;
    ArrayList<Task> taskArr = new ArrayList<Task>(100);
    Pattern m = Pattern.compile("^mark.*$");
    Pattern u = Pattern.compile("^unmark.*$");

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
                    if(m.matcher(input).matches()){
                        input = input.replaceAll("\\D+","");
                        if(Integer. parseInt(input) <= taskArr.size())
                            mark(Integer. parseInt(input));
                        else
                            System.out.println("Out of range!");
                    }else if(u.matcher(input).matches()){
                        input = input.replaceAll("\\D+","");
                        if(Integer. parseInt(input) <= taskArr.size())
                            unmark(Integer. parseInt(input));
                        else
                            System.out.println("Out of range!");
                    }else{
                        taskArr.add(new Task(input));
                        System.out.println("added: " + input);
                    }
            }
        }
    }

    public void exit(){
        System.out.println("Bye. Hope to see you again soon!");
        in.close();
    }

    public void list(){
        for(int i=0;i<taskArr.size();i++){
            System.out.println((i+1) + ".[" + (taskArr.get(i).isDone() ? ("X"):(" ")) + "] " + taskArr.get(i).getDescription());
        }
    }

    public void mark(int i){
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + taskArr.get(i-1).getDescription());
        taskArr.get(i-1).setDone(true);
    }

    public void unmark(int i){
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[ ] " + taskArr.get(i-1).getDescription());
        taskArr.get(i-1).setDone(false);
    }
}
