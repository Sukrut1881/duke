import java.io.IOException;
import java.text.ParseException;

public class Ui {

    public static String line = "    ____________________________________________________________\n";

    /**
     * Constructor to print the welcome message of duke when it is initialised. This is printed using the dukePrint
     * method in the Duke class.
     *
     */


    public Ui(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        Duke.dukePrint("Hello from\n" + logo);


        Duke.dukePrint("     Hello! I'm Duke\n" +
                "     What can I do for you?\n" );
    }

    /**
     * Method containing error message when data fails to load from the duke.txt file.
     *
     */

    void showLoadingError()
    {
        System.out.println("\n    ____________________________________________________________\n" +
                "     ☹ OOPS!!! There was an error loading your list.\n" +
                "    ____________________________________________________________\n");
    }

    /**
     *  Prints the current list of tasks the user has stored
     *
     * @param tasks TaskList containing all of the users task information
     */

    void showList(TaskList tasks)
    {
        System.out.println(line);
        System.out.println("Here are the tasks in your list: \n");
        for (int i = 0; i < tasks.size; i++) {
            int s = i + 1;
            System.out.println("\t" + s + ".  " + tasks.task_list.get(i).toString() + "\n");
        }
        System.out.println(line);

    }

    /**
     * Method to mark a task in the list as done and print the respective message
     *
     * @param tasks TaskList containing all of the users task information
     * @param token String array containing the tokenized input string
     */

    void doneTask(TaskList tasks, String[] token)
    {
        int location = token[1].charAt(0) - '0' - 1;
        tasks.task_list.get(location).markAsDone();
        Duke.dukePrint("     Nice! I've marked this task as done: \n" +
                "\t" + tasks.task_list.get(location).toString() + "\n");
    }

    /**
     * Deletes a task in the list and updates the size of the task list.
     *
     * @param tasks TaskList containing all of the users task information
     * @param location integer corresponding to the index of the task to be deleted
     */

    void deleteTask(TaskList tasks, int location)
    {
        Duke.dukePrint("          Noted. I've removed this task:: \n" +
                "\t" + tasks.task_list.get(location).toString() + "\n"
                + " \t Now you have " + --tasks.size + " tasks in the list \n");
        TaskList.deleteTask(tasks.task_list.get(location).description, tasks);
        tasks.task_list.remove(location);
    }

    /**
     * Adds a new task with the type Todo
     * into the users task list
     *
     * @param tasks TaskList containing all of the users task information
     * @param token String array containing the tokenized input string
     */

    void newTodo (TaskList tasks, String[] token)
    {

        String description = "";

        int size = 0;

        for(int i = 0;i<100; i++)
        {
            if(token[i] == null)
            {
                break;
            }
            size++;
        }

        for (int i = 1; i < size ; i++) {
            description = description.concat(token[i] + " ");
        }

        try {
            tasks.task_list.add(new Todo(description , "T"));
            TaskList.newTask(tasks.task_list.get(tasks.size));
        }
        //Exception for Level-5
        catch (DukeException | IOException ex) {
            System.err.print(ex);
        }

        Task newTask = tasks.task_list.get(tasks.size);
        Duke.dukePrint("\t  Got it. I've added this task: \n" +
                "\t" + newTask.toString() + "\n" +
                " \t Now you have " + ++tasks.size + " tasks in the list \n");
    }

    /**
     * Finds multiple tasks in the list which contain and match a specific keyword
     *
     * @param tasks TaskList containing all of the users task information
     * @param token String array containing the tokenized input string
     */

    void findTasks(TaskList tasks, String[] token)
    {
        System.out.println(line + " Here are the matching tasks in your list: \n");
        int matches = 0;
        token[1] = token[1]+ " ";
        for (int i = 0; i < tasks.size; i++) {
            if(tasks.task_list.get(i).description.contains(token[1]))
            {
                System.out.println("\t"+ matches+"."+ tasks.task_list.get(i).toString());
                matches++;
            }
        }
        System.out.println(line);
    }

    /**
     * Adds a new task with the type Deadline or Event
     * into the users task list
     *
     * @param tasks TaskList containing all of the users task information
     * @param token String array containing the tokenized input string
     * @param type String to specify which task type object to instantiate
     * @throws ParseException exception thrown when the input format of the date is incorrect
     */

    void newDeadline_Event( TaskList tasks, String[] token, String type) throws ParseException {
        String description = "";
        String by_at = "";
        boolean flag = true;
        int size = 0;
        String type1 = type.equals("/by")?"D":"E";

        for(int i = 0; i<100; i++) {
            if (token[i] == null) {
                break;
            }
            size++;
        }

        for (int i = 1; i < size ; i++) {
            if (token[i].equals(type)) {
                flag = false;
                continue;
            }
            if (flag) {
                description = description.concat(token[i] + " ");
            } else {
                by_at = by_at.concat(token[i] + " ");
            }
        }
        // changing the by if it is a date

        by_at = Parser.detectDate(by_at);

        try {
            if(type1.equals("D")) {
                tasks.task_list.add(new Deadline(description, by_at, type1));
                tasks.task_list.get(tasks.size).by = by_at;
            }
            else
            {
                tasks.task_list.add(new Event(description, by_at, type1));
                tasks.task_list.get(tasks.size).at = by_at;
            }
            TaskList.newTask(tasks.task_list.get(tasks.size));

        } catch (DukeException | IOException ex) {
            //Exception for Level-5

            System.err.print(ex);
        }
        //Printing out the new Deadline
        Task omg = tasks.task_list.get(tasks.size);
        Duke.dukePrint("\t  Got it. I've added this task: \n" +
                "\t" + omg.toString() + "\n" +
                " \t Now you have " + ++tasks.size + " tasks in the list \n");
    }

}
