public class Task {
    protected String description;
    protected boolean isDone;
    protected char type;

    public Task(String description) {
        String [] reply=description.split(" ");
       if(reply[0].equalsIgnoreCase("todo")){
          this.type='T';
       }else if(reply[0].equalsIgnoreCase("event")){
           this.type='E';
       }else if(reply[0].equalsIgnoreCase("deadline")){
           this.type='D';
       }
        this.description = description;
        this.isDone = false;
    }
   public char getType(){
        return type;
   }
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }
    @Override
    public String toString() {
        return "description: " + description;
    }
}