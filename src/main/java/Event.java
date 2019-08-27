public class Event extends Task
{
    protected String at;

    public Event(String description, String at) throws DukeException
    {
        super(description);
        this.at = at;

        if (description.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }
        else if (at.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The time at of an event cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
