public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = by;

        if (description.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}