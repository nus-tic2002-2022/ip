import javax.sql.rowset.spi.SyncProvider;

public class Print {
    public Print(){

    }
    public Print(String msg){
        String header="";
        if (msg.equalsIgnoreCase("bye")) {
            System.out.println("\t================================");
            System.out.println("\tBye. Hope to see you again soon!");
            System.out.println("\t================================");
        } else {
            for (int i=0; i<msg.length();i++) {
                header+="=";
            }
            System.out.println("\t"+header);
            System.out.println("\t"+msg);
            System.out.println("\t"+header);

        }
    }
}
