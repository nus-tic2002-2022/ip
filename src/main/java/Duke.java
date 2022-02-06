import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isExit = false;
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        String[] text = new String[100];
        int countNo = 0;
        while(!isExit){
            try{
                String inputData;
                Scanner s = new Scanner(System.in);
                inputData = s.nextLine();
                if(inputData.equals("bye")){
                    System.out.println("Bye. Hope to see you again soon!");
                    isExit = true;
                } else if(inputData.equals("list")){
                    for(String t: text){
                        countNo++;
                        if(t != null){
                            System.out.println(countNo +". " + t);
                        }
                    }
                } else{
                    for(int i = 0; i < text.length; i++) {
                        if (text[i] == null) {
                            text[i] = inputData;
                            break;
                        }
                    }
                    System.out.println("added: " +inputData);
                }
            } catch(Exception e){
                System.out.println(e.toString());
            }
        }

    }
}
