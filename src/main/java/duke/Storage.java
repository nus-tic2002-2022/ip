/**
 * Storage handles the writing and reading of list file (duke.txt).
 */
package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String fileName;// = "data\\duke.txt";

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void writeFile(TaskList list) throws IOException {

        FileWriter myFile = new FileWriter(fileName); //filePath+

        for (int i = 0; i < TaskList.getListLength(); i++) {
            myFile.write(TaskList.getTask(i));
            myFile.write("\n");
        }
        myFile.close();

    }

    public void readFile() throws IOException {

        File myFile = new File(this.fileName); //filePath+

        if (myFile.createNewFile()) {
            System.out.print("A new list file has been created for you. ");
        } else {
            Scanner s = new Scanner(myFile);
            System.out.print("The list file already exists and has been loaded for you. ");
            while (s.hasNext()) {
                String line = s.nextLine();
                int index;
                //add to list
                if (line.startsWith("[T] ")) { //to do "[T] [X] "
                    TaskList.addTodo(line.substring(8, line.length())); //
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(5) == 'X') {
                        TaskList.markDone(index);
                    }
                } else if (line.startsWith("[D] ")) { //deadline
                    TaskList.addDeadline(line.substring(8, line.length()-1));
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(5) == 'X') {
                        TaskList.markDone(index);
                    }
                } else if (line.startsWith("[E] ")) { //event
                    TaskList.addEvent(line.substring(8, line.length()-1));
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(5) == 'X') {
                        TaskList.markDone(index);
                    }
                }
            }
        }
    }

}



