import java.util.Scanner;
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

        String initial = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________";


        System.out.println(initial);

        Task [] string_array = new Task[100];

        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();
        int counter = 0;

        while (!(input1.equals("bye")))
        {
            if ( input1.equals("list"))
            {
                System.out.println("    ____________________________________________________________\n" );
                for (int i = 0; i < counter; i++ )
                {
                    int s = i+1;
                    System.out.println("\t"+ s + "." + " [" + string_array[i].getStatusIcon()+ "] "+ string_array[i].description + "\n");
                }
                System.out.println ("    ____________________________________________________________");
            }
            else if (input1.contains("done ") && isInteger(String.valueOf(input1.charAt(5))))
            {
                int location = input1.charAt(5)-'0'-1;
//                System.out.println(location);
                string_array[location].markAsDone();
                System.out.println("    ____________________________________________________________\n" +
                        "     Nice! I've marked this task as done: \n" +
                        "       [" + string_array[location].getStatusIcon()+ "]" + string_array[location].description +  "\n" +
                        "    ____________________________________________________________");
            }
            else
            {
                Task t = new Task(input1);
                string_array[counter]= t;
                System.out.println("    ____________________________________________________________\n" +
                        "     added: "+ " [" + string_array[counter].getStatusIcon()+ "] " +string_array[counter].description+ "\n" +
                        "    ____________________________________________________________");
                counter++;
            }
            input1 = input.nextLine();
        }

        String exit = "    ____________________________________________________________\n" +
            "     Bye. Hope to see you again soon!\n" +
            "    ____________________________________________________________";

        System.out.println(exit);
    }
}
