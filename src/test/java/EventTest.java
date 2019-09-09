import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTest {

    @Test
    void invalidTodoException()
    {
        try
        {
            Event d = new Event ("This is a great event" , "" , "E");
        }
        catch (DukeException e) {
            assertEquals("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The time at of an event cannot be empty.\n" +
                    "    ____________________________________________________________\n", e.getMessage());
        }
    }
}
