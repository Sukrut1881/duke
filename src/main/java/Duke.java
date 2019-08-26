import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

public class Duke {
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }
    public static void main(String[] args) throws IOException, DukeException {

        //Intro page to DUKE:

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

        //Making an array of objects

        Task [] tasks = new Task[100];

        int counter = 0;

        //Reading in the input from a previous session

        FileReader fileReader = new FileReader("src/data/duke.txt");
        BufferedReader br = new BufferedReader(fileReader);
        String str;
        while((str = br.readLine()) != null){
            String delims = "[|]";
            String[] tokens = str.split(delims);

            if(tokens[0].equals("T")){
                tasks[counter] = new Todo(tokens[2]);
                if(tokens[1].equals("true")){
                    tasks[counter].isDone = true;
                }
                counter++;
            } else if(tokens[0].equals("D")){
                tasks[counter] = new Deadline(tokens[2], tokens[3]);
                if(tokens[1].equals("true"))
                {
                    tasks[counter].isDone = true;
                }
                counter++;
            } else if(tokens[0].equals("E")){
                tasks[counter] = new Event(tokens[2], tokens[3]);
                if(tokens[1].equals("true")){
                    tasks[counter].isDone = true;
                }
                counter++;

            }
        }
        br.close();

        //Taking in the first line of input

        Scanner input = new Scanner(System.in);
        String input1 = input.nextLine();


        //Main Loop of the To-do List

        while (!(input1.equals("bye")))
        {
            //Looping the input

            FileWriter fileWriter = new FileWriter("src/data/duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            // Tokenize the input string

            StringTokenizer st = new StringTokenizer(input1);
            int j = 0;
            String [] token = new String [100];
            while (st.hasMoreTokens()) {
                token[j] = st.nextToken();
                j++;
            }

            // Different input cases

            if (token[0].equals("list"))
            {
                //Showing the list

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
                //Marking a task as done

                int location = token[1].charAt(0)-'0'-1;
                tasks[location].markAsDone();
                System.out.println(line +
                        "     Nice! I've marked this task as done: \n" +
                        "\t" + tasks[location].toString() +  "\n" +
                        line);

            }
            else if (token[0].equals("deadline"))
            {
                //Adding a Deadline

                String description = "";
                String by = "";
                boolean flag = true;

                for ( int i = 1; i< j ; i++) {
                    if(token[i].equals("/by")) {
                        flag = false;
                        continue;
                    }
                    if(flag) {
                        description = description.concat(token[i] + " ");
                    }
                    else {
                        by = by.concat(token[i] + " ");
                    }
                }
                try {
                    tasks[counter] = new Deadline(description, by);

                    // Writing to the file

                    printWriter.println("D|" + tasks[counter].isDone + "|"+ tasks[counter].description+"|"+by);

                }
                catch(DukeException ex) {
                    //Exception for Level-5

                    System.err.print(ex);
            }
                //Printing out the new Deadline

                System.out.println(line +
                        "\t  Got it. I've added this task: \n"+
                        "\t" + tasks[counter].toString()+ "\n" +
                        " \t Now you have "+ ++counter + " tasks in the list \n" +
                        line);

            }
            else if (token[0].equals("todo"))
            {
                String description = "";

                for ( int i = 1; i< j ; i++)
                {
                        description = description.concat(token[i] + " ");
                }
                try {
                    tasks[counter] = new Todo(description);
                    // Writing to the file
                    printWriter.println("T|" + tasks[counter].isDone + "|"+ tasks[counter].description);
                }
                //Exception for Level-5
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
                    //New Event
                    tasks[counter] = new Event(description, at);
                    // Writing to the file
                    printWriter.println("E|" + tasks[counter].isDone + "|"+ tasks[counter].description+"|"+at);
                }
                //Exception for Level-5
                catch (DukeException ex) {
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
                // Error Type 2 in Level-5

                DukeException d = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("    ____________________________________________________________\n" +
                        d.getMessage() + "\n" +
                        "    ____________________________________________________________");

            }

            // Take in the next line of input

            printWriter.close();
            fileWriter.close();
            input1 = input.nextLine();
        }

        // Update the .txt file at the end of the session

        FileReader fileReader1 = new FileReader("src/data/duke.txt");
        BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
        StringBuilder inputBuffer = new StringBuilder();
        String str1;
        int counter1 = 0;
        while((str1 = bufferedReader1.readLine()) != null) {
            String delims = "[|]";
            String[] tokens1 = str1.split(delims);
            if (tasks[counter1].isDone) {
                tokens1[1] = "true";
            }
            StringBuilder line1 = new StringBuilder();
            for (int i = 0; i < tokens1.length; i++) {
                line1.append(tokens1[i]);
                if (i != tokens1.length - 1) {
                    line1.append("|");
                }
            }
            inputBuffer.append(line1);
            inputBuffer.append('\n');
            counter1++;
        }
        fileReader1.close();

        FileOutputStream fileOut = new FileOutputStream("src/data/duke.txt");
        fileOut.write(inputBuffer.toString().getBytes());
        fileOut.close();

        //Print the exit line

        String exit = line +
            "     Bye. Hope to see you again soon!\n" +
            line;
        System.out.println(exit);
    }
}
