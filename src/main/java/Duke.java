import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String line = "    ____________________________________________________________\n";

        String initial = line +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                line;

        System.out.println(initial);

        Task [] tasks = new Task[100];

        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();

        int counter = 0;

        int flag1 = 1;

        while (!(input1.equals("bye")))
        {
            StringTokenizer st = new StringTokenizer(input1);
            int j = 0;
            String [] token = new String [100];
            while (st.hasMoreTokens()) {
                token[j] = st.nextToken();
                j++;
            }
            if (token[0].equals("list"))
            {
                System.out.println(line);
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 0; i < counter; i++ )
                {
                    int s = i+1;
                    System.out.println("\t"+ s + ".  " + tasks[i].toString() + "\n");
                }
                System.out.println (line);
            }
            else if (token[0].equals("done"))
            {
                int location = token[1].charAt(0)-'0'-1;
                tasks[location].markAsDone();
                System.out.println(line +
                        "     Nice! I've marked this task as done: \n" +
                        "\t" + tasks[location].toString() +  "\n" +
                        line);
            }
            else if (token[0].equals("deadline"))
            {
                String description = "";
                String by = "";
                boolean flag = true;

                for ( int i = 1; i< j ; i++)
                {
                    if(token[i].equals("/by"))
                    {
                        flag = false;
                        continue;
                    }
                    if(flag)
                    {
                        description = description.concat(token[i] + " ");
                    }
                    else
                    {
                        by = by.concat(token[i] + " ");
                    }
                }
                try {
                    tasks[counter] = new Deadline(description, by);
                }
                catch(DukeException ex) {
                System.err.print(ex);
            }
                System.out.println(line +
                        "\t  Got it. I've added this task: \n"+
                        "\t" + tasks[counter].toString()+ "\n" +
                        " \t Now you have "+ ++counter + " tasks in the list \n" +
                        line);
            }
            else if (token[0].equals("todo"))
            {
                String description = "";
                boolean flag = true;

                for ( int i = 1; i< j ; i++)
                {
                        description = description.concat(token[i] + " ");
                }
                try {
                    tasks[counter] = new Todo(description);
                }
                catch(DukeException ex) {
                    System.err.print(ex);
                }
                System.out.println(line +
                        "\t  Got it. I've added this task: \n"+
                        "\t" + tasks[counter].toString()+ "\n" +
                        " \t Now you have "+ ++counter + " tasks in the list \n" +
                        line);
            }
            else if (token[0].equals("event"))
            {
                String description = "";
                String at = "";
                boolean flag = true;

                for (int i = 1; i < j; i++) {
                    if (token[i].equals("/at")) {
                        flag = false;
                        continue;
                    }
                    if (flag) {
                        description = description.concat(token[i] + " ");
                    } else {
                        at = at.concat(token[i] + " ");
                    }
                }

                try {
                    tasks[counter] = new Event(description, at);
                } catch (DukeException ex) {
                    System.err.print(ex);
                }
                System.out.println(line +
                        "\t  Got it. I've added this task: \n" +
                        "\t" + tasks[counter].toString() + "\n" +
                        " \t Now you have " + ++counter + " tasks in the list \n" +
                        line);
            }
            else
            {
                DukeException d = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ____________________________________________________________\n" +
                        d.getMessage() + "\n" +
                        "    ____________________________________________________________");

            }
            input1 = input.nextLine();
        }

        String exit = line +
            "     Bye. Hope to see you again soon!\n" +
            line;
        System.out.println(exit);
    }
}
