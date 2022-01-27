import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("---------------------------------");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("---------------------------------");

        String line;
        Scanner in = new Scanner(System.in);
        String[] myList;
        myList = new String[100];
        int listCount = 0;

        boolean start = true;

        while (start){
            line = in.nextLine();
            if(line.equalsIgnoreCase("bye")){
                System.out.println("---------------------------------");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("---------------------------------");
                start = false;
            }else if(line.equalsIgnoreCase("list")){
                System.out.println("---------------------------------");
                if(listCount == 0){
                    System.out.println("nothing to print");
                }
                for(int i = 0; i < listCount; i++){
                    System.out.println(i+1 + ". " + myList[i]);
                }
                System.out.println("---------------------------------");
            }
            else{
                myList[listCount] = line;
                listCount++;
                System.out.println("---------------------------------");
                System.out.println("added: " + line);
                System.out.println("---------------------------------");
            }
        }
    }
}
