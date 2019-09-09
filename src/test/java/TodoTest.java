import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void invalidTodoException()
    {
        try
        {
            Todo d = new Todo ("" , "T");
        }
        catch (DukeException e) {
            assertEquals("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a Todo cannot be empty.\n" +
                    "    ____________________________________________________________\n", e.getMessage());
        }
    }
}
