public class Deadline extends Task {

    protected String by;

    /**
     * Constructor which is called when a new Deadline object is instantiated. Also throws an error
     *
     * @param description string containing the task description
     * @param by String variable indicating by when the task should be completed
     * @param type string containing the type of task
     * @throws DukeException exception thrown if either description or the date and time are empty
     */

    public Deadline(String description, String by, String type) throws DukeException {
        super(description);
        this.by = by;
        this.type = type;

        if (description.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }
        else if (by.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The time by of a deadline cannot be empty.\n" +
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
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}