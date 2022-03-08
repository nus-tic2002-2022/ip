public class DukeException extends Exception
{
    private String error;

    public DukeException (String error)
    {
        super(error);
    }
    public String getError() {
        return this.error;
    }

}
