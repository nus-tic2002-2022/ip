import java.util.ArrayList;
import java.util.List;

public class Print {
    String divider="==========================================";
    public Print() {

    }

    public Print(String msg) {


        if (msg.equalsIgnoreCase("bye")) {
            System.out.println("\t================================");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t================================");
        } else {
            System.out.println("\t" + divider);
            System.out.println("\tadded:" + msg);
            System.out.println("\t" + divider);
        }
    }
    public Print(ArrayList<Task> TL){
        if(TL.size()==0){
            System.out.println("There is no Task Currently, wanna add some? :P");
        }
        System.out.println(divider+"\n Here are the tasks in your list:");
        for (int i =0; i<TL.size();i++){
            System.out.println((i+1)+". ["+TL.get(i).getStatusIcon()+"]"+TL.get(i).description);
        }
        System.out.println(divider);
    }

}

