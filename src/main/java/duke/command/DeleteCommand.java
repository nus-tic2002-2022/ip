package duke.command;

import duke.Storage;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;
import java.util.*;

public class DeleteCommand implements Command{
    private final TaskList tasks;
    private Storage storage;

    public DeleteCommand(TaskList tasks, Storage storage){
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Remove a task according to the index input by the user.
     *
     * @param fullCommand fullCommand array of command from the user input.
     * @return message to the user.
     * @throws NumberFormatException catch a error if the user input is not a number.
     * @throws IndexOutOfBoundsException catch a error if the input index is out of bound.
     * @throws IOException if the task cannot be recorded.
     */
    @Override
    public List<String> run(String[] fullCommand)throws NumberFormatException, IndexOutOfBoundsException, IOException{
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

            list.sort(new Comparator<Integer>() {
                public int compare(Integer o1, Integer o2) {
                    if(o1>o2)
                        return -1;
                    if(o1==o2)
                        return 0;
                    return 1;
                }
            });
            for (int i = 0; i < list.size(); i++) {

                Task deleteItem = tasks.remove(list.get(i) - 1);
                storage.store(tasks.convertAsLines());

            }

            return List.of("Nice! I've removed this task as done: " + fullCommand[1]);
        }catch(NumberFormatException e){
            return List.of(" OOPS!!! This is not a number: " + fullCommand[1]);
        }catch(IndexOutOfBoundsException e){
            return List.of(" OOPS!!! The index out of bound: " + fullCommand[1]);
        }

    }
}
