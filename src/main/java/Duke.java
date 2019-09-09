import java.text.ParseException;

public class Duke {
    public static String line = "    ____________________________________________________________\n";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor to begin the duke bot by taking data from the specified file path.
     *
     * @param filePath file path of the .txt file with the previous sessions
     */

    private Duke(String filePath)
    {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
        try
        {
            tasks = new TaskList(storage.load(0));
        }
        catch (DukeException e)
        {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the duke program. The main method which contains a while loop to keep accepting user inputs to alter their
     * list
     *
     */

    public void run() throws ParseException {

        String input_string;
        input_string = parser.inputString();

        while (!(input_string.equals("bye"))) {

            String[] token = parser.tokenize(input_string);

            switch (token[0]) {
                case "list":

                    ui.showList(tasks);
                    break;

                case "done":

                    ui.doneTask(tasks, token);
                    break;

                case "delete":
                    int location = token[1].charAt(0) - '0' - 1;
                    ui.deleteTask(tasks,location);
                    break;

                case "deadline":
                    String type = "/by";
                    ui.newDeadline_Event(tasks, token, type);
                    break;

                case "todo":
                    ui.newTodo(tasks, token);
                    break;

                case "event":
                    String type1 = "/at";
                    ui.newDeadline_Event(tasks, token, type1);
                    break;

                case "find":

                    ui.findTasks(tasks, token);
                    break;

                default:

                    DukeException d = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    dukePrint(d.getMessage());
                    break;
            }

            input_string = parser.inputString();
        }

        storage.exitFile(tasks);

        dukePrint("     Bye. Hope to see you again soon!\n");
    }

    /**
     * Main function. Starting point of the program. Instantiates the duke object and specifies the filepath
     *
     */

    public static void main(String[] args) throws ParseException {
        new Duke("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt").run();
    }

    /**
     * Takes any given message string and prints it to the user with the relevant duke formatting which includes the
     * solid lines
     *
     * @param message TaskList containing all of the users task information
     */

    public static void dukePrint(String message)
    {
        String dukeOutput = line +
                message+"\n" +
                line;
        System.out.println(dukeOutput);
    }
}
