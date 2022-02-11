public class taskList {

    public String[] List = new String[100];
    protected int counter;
    String line = "____________________________________________________________";

    public taskList() {
        counter = 0;
    }

    public void addTask(String task) {
        this.List[counter] = task;
        this.counter++;
        System.out.println("added: " + task);
        System.out.println(line);

    }

    public void printList() {
        for (int i = 0; i < this.counter; i++) {
            System.out.println(i+1 + ". " + this.List[i]);
        }
    }
}
