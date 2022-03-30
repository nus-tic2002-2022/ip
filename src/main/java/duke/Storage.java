/**
 * Storage handles the writing and reading of list file (duke.txt).
 *
 * @throws IOException if file is not found or cannot be created or saved.
 *
 */
package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String fileName;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    public void readFile() throws IOException {

        File myFile = new File(this.fileName);
        final int START_INDEX_CLEAN = 8; //starting index for clean format (format in the file)
        final int STATUS_ICON_INDEX_CLEAN = 5; //status icon index for clean format (format in the file)

        if (myFile.createNewFile()) {
            System.out.print("A new list file has been created for you. ");
        } else {
            Scanner s = new Scanner(myFile);
            System.out.print("The list file already exists and has been loaded for you. ");
            while (s.hasNext()) {
                String line = s.nextLine();
                int index;

                if (line.startsWith("[T] ")) { //to do
                    TaskList.addTodo(line.substring(START_INDEX_CLEAN, line.length())); //
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(STATUS_ICON_INDEX_CLEAN) == 'X') {
                        TaskList.markDone(index);
                    }
                } else if (line.startsWith("[D] ")) { //deadline
                    TaskList.addDeadline(line.substring(START_INDEX_CLEAN, line.length()-1));
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(STATUS_ICON_INDEX_CLEAN) == 'X') {
                        TaskList.markDone(index);
                    }
                } else if (line.startsWith("[E] ")) { //event
                    TaskList.addEvent(line.substring(START_INDEX_CLEAN, line.length()-1));
                    index = TaskList.getListLength() - 1;
                    if(line.charAt(STATUS_ICON_INDEX_CLEAN) == 'X') {
                        TaskList.markDone(index);
                    }
                }
            }
        }
    }

    public void writeFile(TaskList list) throws IOException {

        FileWriter myFile = new FileWriter(fileName); //filePath+

        for (int i = 0; i < TaskList.getListLength(); i++) {
            myFile.write(TaskList.getOneTask(i));
            myFile.write("\n");
        }

        myFile.close();
    }

}



