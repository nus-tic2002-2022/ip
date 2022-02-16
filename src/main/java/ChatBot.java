import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import java.util.Scanner;
import java.util.*;
import java.util.regex.*;

public class ChatBot {
    public Scanner in = new Scanner(System.in);
    String input;
    ArrayList<Task> taskArr = new ArrayList<Task>(100);
    Pattern m = Pattern.compile("^mark\\s[0-9]*$");
    Pattern u = Pattern.compile("^unmark\\s[0-9]*$");
    Pattern t = Pattern.compile("^todo\\s.*$");
    Pattern d = Pattern.compile("^deadline\\s.*$");
    Pattern e = Pattern.compile("^event\\s.*$");
    Pattern rm = Pattern.compile("^delete\\s.*$");

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
                    }else if(rm.matcher(input).matches()){
                        input = input.replaceAll("\\D+","");
                        if(Integer. parseInt(input) <= taskArr.size())
                            delete(Integer. parseInt(input));
                        else
                            System.out.println("Out of range!");
                    }else if(t.matcher(input).matches()){
                        input = input.replaceAll("^todo\\s","");
                        taskArr.add(new Todo(input));
                        System.out.println("Got it. I've added this task: \n" + taskArr.get(taskArr.size() - 1).getDescription() + "\nNow you have " + taskArr.size() + " tasks in the list.");
                    }else if(d.matcher(input).matches()){
                        try{
                            String[] check = input.split("/by");
                            if(check.length < 2){
                                throw new DukeException("Insufficient arguments!");
                            }
                            input = input.replaceAll("^deadline\\s","");
                            String[] splited = input.split("/by", 2);
                            taskArr.add(new Deadline(splited[0], splited[1].replaceAll("^\\s","")));
                            System.out.println("Got it. I've added this task: \n" + taskArr.get(taskArr.size() - 1).getDescription() + "\nNow you have " + taskArr.size() + " tasks in the list.");
                        }catch(DukeException e){
                            System.out.println("Error: " + e.getMessage());
                        }
                    }else if(e.matcher(input).matches()){
                        try{
                            String[] check = input.split("/at");
                            if(check.length < 2){
                                throw new DukeException("Insufficient arguments!");
                            }
                            input = input.replaceAll("^event\\s","");
                            String[] splited = input.split("/at", 2);
                            taskArr.add(new Event(splited[0], splited[1].replaceAll("^\\s","")));
                            System.out.println("Got it. I've added this task: \n" + taskArr.get(taskArr.size() - 1).getDescription() + "\nNow you have " + taskArr.size() + " tasks in the list.");
                        }catch(DukeException e){
                            System.out.println("Error: " + e.getMessage());
                        }
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
            System.out.println((i+1) + ". " + taskArr.get(i).getDescription());
        }
    }

    public void mark(int i){
        taskArr.get(i-1).setDone(true);
        System.out.println("Nice! I've marked this task as done:\n" + taskArr.get(i-1).getDescription());
    }

    public void unmark(int i){
        taskArr.get(i-1).setDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n" + taskArr.get(i-1).getDescription());
    }
    public void delete(int i){
        System.out.println("Noted. I've removed this task: \n" + taskArr.get(taskArr.size() - 2).getDescription() + "\nNow you have " + taskArr.size() + " tasks in the list.");
        taskArr.remove(i-1);
    }

}
