public class Event extends Task
{
    protected String at;

    /**
     * Constructor which is called when a new Event object is instantiated. Also throws an error if either
     * description or the date and time are empty
     *
     * @param description string containing the task description
     * @param at String variable indicating when the event takes place
     * @param type string containing the type of task
     * @throws DukeException exception thrown if either description or the date and time are empty
     */

    public Event(String description, String at, String type) throws DukeException
    {
        super(description);
        this.at = at;
        this.type = type;

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

    /**
     * Returns the string containing the task type, description and the date and time. This is returned to the UI class
     * when printing the list.
     *
     * @return string in the specific format
     */

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
