//import java.io.*;
//import java.util.ArrayList;
//import duke.tasklist.Task;
//
//
//public class save {
//
//    public save(String fileName, ArrayList<Task> TL) throws IOException {
//        File filename = new File(fileName);
//        boolean newFile = filename.createNewFile();
//        FileOutputStream oFile = new FileOutputStream(fileName, false);
//        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
//        for (int i = 0; i < TL.size(); i++) {
//            pw.println(TL.get(i).getType() + " | " + TL.get(i).getStatusIconS() + " | " + TL.get(i).getDescription());
//        }
//        pw.close();
//    }
//}
