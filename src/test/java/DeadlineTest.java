import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void invalidDeadlineException()
    {
        try
        {
            Deadline d = new Deadline ("" , "Non empty", "D");
        }
        catch (DukeException e) {
            assertEquals("\n    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                    "    ____________________________________________________________\n", e.getMessage());
        }
    }
}
