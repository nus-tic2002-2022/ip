import java.lang.*;

public class Event {
    private String activity;
    private boolean status;

    public Event(String activity, boolean status) {
        this.activity = activity;
        this.status = status;
    }
    public Event() {
        this("",false);
    }

    public String getActivity(){
        String output = this.activity;
        return output;
    }

    public boolean getStatus(){
        boolean output = this.status;
        return output;
    }
}
