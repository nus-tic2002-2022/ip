package duke.Storage;

import duke.Duke;
import duke.Exception.CannotWriteException;
import duke.Exception.DukeException;
import duke.Exception.FileLoadException;
import duke.Exception.dateparseException;
import duke.Tasklist.*;
import duke.UI.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.ArrayList;

public class fileaccess {

    private static File loadedfile;
    private static String f_p;
        public fileaccess (String filepath)
        {
            this.f_p = filepath;
            this.loadedfile= new File(filepath);
        }
        public static void load(ArrayList<Task>tasklist) throws FileLoadException
        {
            Scanner s = null;
            try {
                s = new Scanner(loadedfile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            while (s.hasNext()) {
                String[] str = new String [20];
                str = s.nextLine().split("[|] ", 5);
                //System.out.println(str.length);
                if (str[0].trim().replace("\\s", "").equals("T")) {
                    ToDo t_flag = new ToDo(str[2].trim());  // str[0] = T , str[1]= 1 or 0 , str[2] description, str[3] details
                    if (str[1].trim().equals("1")) {
                        t_flag.setDone();
                    }
                    if (str[1].trim().equals("0") ) {
                        t_flag.set_unDone();
                    }
                    tasklist.add(t_flag);
                    System.out.println(t_flag.getStatus());
                } else if (str[0].trim().replace("\\s", "").equals("E")) {
                    Events t_flag = new Events(str[2].trim(), str[3].trim());
                    if (str[1].trim().equals("1")) {
                        t_flag.setDone();
                    }
                    if (str[1].trim().equals("0") ) {
                        t_flag.set_unDone();
                    }
                    tasklist.add(t_flag);
                    System.out.println(t_flag.getStatus());
                } else if (str[0].trim().replace("\\s", "").equals("D")) {
                    try {
                        for(String a: str)
                        {
                            System.out.println(a);
                        }
                        String [] datetime_trim = str[3].split(" ");
                        Deadlines t_flag = new Deadlines(str[2].trim(), str[3]);
                        if (str[1].trim().equals("1")) {
                            t_flag.setDone();
                        }
                        if (str[1].trim().equals("0") ) {
                            t_flag.set_unDone();
                        }
                        tasklist.add(t_flag);
                        System.out.println(t_flag.getStatus());
                    }
                    catch (dateparseException e)
                    {
                        System.out.println("Date format wrong");

                    }
                    catch (ParseException e)
                    {
                        System.out.println("Time format wrong");
                    }
                    catch (DukeException e)
                    {
                        System.out.println("Error in Command");
                    }
                    catch (IndexOutOfBoundsException e)
                    {
                        System.out.println("Index out of Bound Error");
                    }
                    catch (DateTimeParseException e)
                    {
                        System.out.println("Date Time Parse Error");
                    }
                } else {
                    continue;
                }
            }
        }
        public static void printFileContents(File f) throws FileNotFoundException
        {
                Scanner s = new Scanner(f);
                while (s.hasNext()) {
                    System.out.println(s.nextLine());
                }
        }
       public static void writetoFile(ArrayList <Task> tasklist) throws CannotWriteException, IOException {

                FileWriter fw = new FileWriter(f_p, false);
                for (Task s : tasklist)
                {
                    String w = Parser.parsing_to_write(s);
                    fw.write(w+"\n");

                }
                fw.close();

        }

}
