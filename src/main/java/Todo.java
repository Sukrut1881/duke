public class Todo extends Task {

    /**
     * Constructor which is called when a new Todo object is instantiated. Also throws an error if the
     * description is empty
     *
     * @param description string containing the task description
     * @param type string containing the type of task
     * @throws DukeException exception thrown if either description is empty
     */

    public Todo(String description, String type) throws DukeException{
        super(description);
        this.type = type;

        if (description.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a Todo cannot be empty.\n" +
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
        return "[T]" + super.toString();
    }
}