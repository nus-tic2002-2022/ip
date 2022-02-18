import java.util.Scanner;

public class Duke {

    public static Task[] taskList = new Task[100];

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str;
        str = in.nextLine();

        int count = 0;
        
        while (!str.equals("bye")){
            if(str.equals("list"))
            {
                System.out.println("________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i<count ; i++ ){
                    System.out.println(i+1 + ". " + "[" + taskList[i].getStatusIcon() + "] " + taskList[i].getDescription());
                }
                System.out.println("________________________________");
            }
			else if (str.contains("unmark")){
                System.out.println("________________________________");
                String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                int index = Integer.parseInt(input[1]);
                taskList[index -1].markAsNotDone();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("[" + taskList[index-1].getStatusIcon() + "] " + taskList[index-1].getDescription());
                System.out.println("________________________________");

            }
            else if (str.contains("mark")){
                System.out.println("________________________________");
                String[]input = str.split(" ");     // input[0] = mark, input[1] = "2"
                int index = Integer.parseInt(input[1]);
                taskList[index -1].markAsDone();
                System.out.println("Nice! I've marked this as done:");
                System.out.println("[" + taskList[index-1].getStatusIcon() + "] " + taskList[index-1].getDescription());
                System.out.println("________________________________");

            }
            else{
                Task taskAdded = new Task(str);
                System.out.println("added: " + str);
                taskList[count] = taskAdded;
                count++;
            }
            //System.out.println("________________________________");
            str = in.nextLine();
        }
        System.out.println("________________________________\n");
        System.out.println("Bye. Hope to see you again soon!");
    }

}
