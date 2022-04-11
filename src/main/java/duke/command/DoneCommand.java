package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DoneCommand implements Command{
    private final TaskList tasks;
    private Storage storage;

    public DoneCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Marks the task as done and returns the message to user.
     *
     * @param fullCommand array of command from the user input.
     * @return a message to user.
     * @throws NumberFormatException catch an error if the user input is not a number.
     * @throws IndexOutOfBoundsException catch an error if the input index is out of bound.
     * @throws IOException if the task cannot be recorded.
     */
    @Override
    public List<String> run(String[] fullCommand) throws NumberFormatException, IndexOutOfBoundsException, IOException{
        try{
            List<Integer>list = new ArrayList<>();
            String[] sarray;
            if (fullCommand[1].contains("-")){
                sarray=fullCommand[1].split("-");
                int start = Integer.parseInt(sarray[0]),end=Integer.parseInt(sarray[1]);
                for (int i = start;i<=end;i++){
                    list.add(i);
                }
            }else {
                sarray = fullCommand[1].split(",");

                for (int i = 0; i < sarray.length; i++) {
                    list.add(Integer.parseInt(sarray[i]));
                }
            }
            List<Integer>integerList = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {

                Task markItem = tasks.get(Integer.parseInt(sarray[i]) - 1);
                markItem.markAsDone();
                storage.store(tasks.convertAsLines());

            }

            return List.of("Nice! I've marked this task as done: " + fullCommand[1]);
        }catch(NumberFormatException e){
            return List.of(" OOPS!!! This is not a number: " + fullCommand[1]);
        }catch(IndexOutOfBoundsException e){
            return List.of(" OOPS!!! The index out of bound: " + fullCommand[1]);
        }
    }
}
