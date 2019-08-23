import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        String initial = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";


        System.out.println(initial);

        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();

        while ( !(input1.equals("bye")))
        {
            System.out.println("    ____________________________________________________________\n" +
                        "\t" +input1 + "\n" +
                    "    ____________________________________________________________");
            input1 = input.nextLine();
        }

        String exit = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        System.out.println(exit);
    }
}
