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

        String [] string_array = new String[100];

        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        int counter = 0;

        while ( !(input1.equals("bye")))
        {
            if ( input1.equals("list"))
            {
                System.out.println("    ____________________________________________________________\n" );
                for (int i = 0; i < counter; i++ )
                {
                    System.out.println("\t"+ i + "." + " " + string_array[i] + "\n");
                }
                System.out.println ("    ____________________________________________________________");
            }
            else
            {
                string_array[counter] = input1;
                counter++;
                System.out.println("    ____________________________________________________________\n" +
                        "     added: "+ input1+ "\n" +
                        "    ____________________________________________________________");
            }
            input1 = input.nextLine();
        }

        String exit = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        System.out.println(exit);
    }
}
