public class Todo extends Task {

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

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}