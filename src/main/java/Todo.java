public class Todo extends Task {

    public Todo(String description) throws DukeException{
        super(description);
        if (description.equals(""))
        {
            throw new DukeException("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a Todo cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}