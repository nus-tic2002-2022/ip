package duke.command;

import duke.Storage.fileaccess;
import duke.Tasklist.RecurringTask;
import duke.Tasklist.Task;
import duke.UI.UI;
import java.time.LocalDate;
import java.util.ArrayList;


public class Add_Recur_Command extends Command{
    protected RecurringTask passed_task;
    public Add_Recur_Command (String passed, RecurringTask t_passed)
    {
        super(passed);
        this.passed_task = t_passed;
    }
    @Override
    public boolean isExit() {
        return false;
    }
    @Override
    public void execute(ArrayList<Task> tasklist, UI ui, fileaccess f) {
        for(Integer i = 0; i < passed_task.getnumber(); i+=1) {
            System.out.println("What is : " + i);

            RecurringTask n_passed_task = null;
            n_passed_task = passed_task;
            n_passed_task.add_day(i);
            System.out.println (passed_task.getDetails());
            tasklist.add(n_passed_task);
            n_passed_task = null;

        }

    }
}
