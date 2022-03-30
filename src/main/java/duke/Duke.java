package duke;

import duke.Exception.CannotWriteException;
import duke.Exception.DukeException;
import duke.Exception.FileLoadException;
import duke.Storage.fileaccess;
import duke.Tasklist.Parser;
import duke.Tasklist.Task;
import duke.UI.UI;
import duke.command.Command;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

public class Duke {

    private fileaccess f;
    private UI user_interface;
    private static ArrayList<Task> tasklist = new ArrayList<>();
    public static String basepath = new File("").getAbsolutePath();
    private static String filepath = basepath + "/buffer.txt";


    //declare variable file path should be relative file path, useable in different OS

    public Duke(String filepath)

    {
        this.user_interface = new UI();
        //e = new duke.echo();
        f = new fileaccess(filepath);
        try{
            f.load(tasklist);
        }
        catch (FileLoadException e)
        {
            System.out.println("Error in loading file");
        }

    }
    public void run()
    {
        user_interface.showWelcome();
        boolean isExit = false;
        while (!isExit)
        {
            try{
                String command = user_interface.readCommand();
                user_interface.showLine();
                Command c = Parser.parsing(command);
                c.execute(tasklist,user_interface,f);
                isExit = c.isExit();
            }
            catch (NullPointerException e)
            {
                System.out.println("Null pointer exception");
            }
            catch (DukeException e)
            {
                System.out.println("Error in command");
            }
            finally
            {
                try{
                    f.writetoFile(tasklist);
                    user_interface.showLine();
                }
                catch (CannotWriteException e)
                {
                    System.out.println("Cannot write");
                }
                catch (IOException e)
                {
                    System.out.println("Error in creating file");
                }

            }
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        //System.out.println("Hello! I'm duke.Duke");
        //System.out.println("What can I do for you");

        //Duke project = new Duke(filepath);
       // project.run();
    }
}
