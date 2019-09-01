import java.io.*;
import java.nio.channels.ScatteringByteChannel;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;

public class Duke {

    private static int counter = 0;

    //Making an array of objects

    private static ArrayList<Task> tasks = new ArrayList<>();

    private static  SimpleDateFormat formatter_st = new SimpleDateFormat("dd 'st' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_nd = new SimpleDateFormat("dd 'nd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_rd = new SimpleDateFormat("dd 'rd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_th = new SimpleDateFormat("dd 'th' 'of' MMMMMMMMMMMM yyyy, h:mm a");

    private static String line = "    ____________________________________________________________\n";

    public static void main(String[] args) throws IOException, DukeException, ParseException {

        //Intro page to DUKE:

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        dukePrint("Hello from\n" + logo);


        dukePrint("     Hello! I'm Duke\n" +
                "     What can I do for you?\n" );

        startFile();

        //Taking in the first line of input

        String input1 = inputString();

        //Main Loop of the To-do List

        while (!(input1.equals("bye"))) {
            //Looping the input

            // Tokenize the input string

            StringTokenizer st = new StringTokenizer(input1);
            int j = 0;
            String[] token = new String[100];
            while (st.hasMoreTokens()) {
                token[j] = st.nextToken();
                j++;
            }

            // Different input cases

            if (token[0].equals("list")) {
                //Showing the list

                System.out.println(line);
                System.out.println("Here are the tasks in your list: \n");
                for (int i = 0; i < counter; i++) {
                    int s = i + 1;
                    System.out.println("\t" + s + ".  " + tasks.get(i).toString() + "\n");
                }
                System.out.println(line);

            } else if (token[0].equals("done")) {
                //Marking a task as done

                int location = token[1].charAt(0) - '0' - 1;
                tasks.get(location).markAsDone();
                dukePrint("     Nice! I've marked this task as done: \n" +
                        "\t" + tasks.get(location).toString() + "\n");

            } else if(token[0].equals("delete")){
                int location = token[1].charAt(0) - '0' - 1;
                dukePrint("          Noted. I've removed this task:: \n" +
                        "\t" + tasks.get(location).toString() + "\n"
                        + " \t Now you have " + --counter + " tasks in the list \n");
                deleteTask(tasks.get(location).description);
                tasks.remove(location);
            }
            else if (token[0].equals("deadline")) {
                //Adding a Deadline

                String description = "";
                String by = "";
                boolean flag = true;

                for (int i = 1; i < j; i++) {
                    if (token[i].equals("/by")) {
                        flag = false;
                        continue;
                    }
                    if (flag) {
                        description = description.concat(token[i] + " ");
                    } else {
                        by = by.concat(token[i] + " ");
                    }
                }
                // changing the by if it is a date

                 by = detectDate(by);

                try {
                    tasks.add(new Deadline(description, by, "D"));
                } catch (DukeException ex) {
                    //Exception for Level-5

                    System.err.print(ex);
                }

                //Printing out the new Deadline

                dukePrint("\t  Got it. I've added this task: \n" +
                        "\t" + tasks.get(counter).toString() + "\n" +
                        " \t Now you have " + ++counter + " tasks in the list \n");

            } else if (token[0].equals("todo")) {
                String description = "";

                for (int i = 1; i < j; i++) {
                    description = description.concat(token[i] + " ");
                }
                try {
                    tasks.add(new Todo(description, "T"));
                }
                //Exception for Level-5
                catch (DukeException ex) {
                    System.err.print(ex);
                }
                dukePrint("\t  Got it. I've added this task: \n" +
                        "\t" + tasks.get(counter).toString() + "\n" +
                        " \t Now you have " + ++counter + " tasks in the list \n");

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
                at=detectDate(at);
                try {
                    //New Event
                    tasks.add(new Event(description, at, "E"));
                }
                //Exception for Level-5
                catch (DukeException ex) {
                    System.err.print(ex);
                }
                System.out.println(line +
                        "\t  Got it. I've added this task: \n" +
                        "\t" + tasks.get(counter).toString() + "\n" +
                        " \t Now you have " + ++counter + " tasks in the list \n" +
                        line);
            }
            else if(token[0].equals("find"))
            {
                System.out.println(line + " Here are the matching tasks in your list: \n");
                int matches = 0;
                token[1] = token[1]+ " ";
                for (int i = 0; i < tasks.size(); i++) {
                    if(tasks.get(i).description.contains(token[1]))
                    {
                        System.out.println("\t"+ matches+"."+ tasks.get(i).toString());
                        matches++;
                    }
                }
                System.out.println(line);
            }
            else {
                // Error Type 2 in Level-5
                DukeException d = new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                dukePrint(d.getMessage());
            }

            // Take in the next line of input

            input1 = inputString();
        }

        //Function to save the list

        exitFile();

        //Print the exit line

        dukePrint("     Bye. Hope to see you again soon!\n");
    }

    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    public static String inputString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public static void dukePrint(String message)
    {
        String dukeOutput = line +
                message+"\n" +
                line;
        System.out.println(dukeOutput);
    }

    public static String detectDate(String day) throws ParseException {

        String output = "";

        if (day.contains("/") && Character.isDigit(day.charAt(0))) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            Date date = formatter.parse(day);

            if (day.charAt(1) == '1' || (day.charAt(0) == '1' && day.charAt(1) == '/')) {
                if (day.charAt(0) == '1' && day.charAt(1) == '1') {
                    output = formatter_th.format(date);
                } else {
                    output = formatter_st.format(date);
                }
            } else if (day.charAt(1) == '2' || (day.charAt(0) == '2' && day.charAt(1) == '/')) {
                if (day.charAt(0) == '1' && day.charAt(1) == '2') {
                    output = formatter_th.format(date);
                } else {
                    output = formatter_nd.format(date);
                }
            } else if (day.charAt(1) == '3' || (day.charAt(0) == '3' && day.charAt(1) == '/')) {
                if (day.charAt(0) == '1' && day.charAt(1) == '3') {
                    output = formatter_th.format(date);
                } else {
                    output = formatter_rd.format(date);
                }
            } else {
                output = formatter_th.format(date);
            }

        }
        else
        {
            return day;
        }
        return output;
    }
    public static void deleteTask(String description) {

        // Update the .txt file when the thing is deleted

        try {
            FileReader fileReader1 = new FileReader("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            StringBuilder inputBuffer = new StringBuilder();
            String str1;
            while ((str1 = bufferedReader1.readLine()) != null && tasks.size()!=0) {
                String delims = "[|]";
                String[] tokens1 = str1.split(delims);
                if (!tokens1[2].equals(description)) {
                    StringBuilder line1 = new StringBuilder();
                    for (int i = 0; i < tokens1.length; i++) {
                        line1.append(tokens1[i]);
                        if (i != tokens1.length - 1) {
                            line1.append("|");
                        }
                    }
                    inputBuffer.append(line1);
                    inputBuffer.append('\n');
                }
            }
            fileReader1.close();

            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }
        catch (IOException e)
        {
            dukePrint(e.getMessage());
        }
    }

    public static void exitFile() {

        // Update the .txt file at the end of the session

        try {

            FileWriter fileWriter = new FileWriter("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt", true);
            PrintWriter printWriter = new PrintWriter(fileWriter);

            for (Task task : tasks) {
                    printWriter.println(task.type+"|" + task.isDone + "|" + task.description + "|" + task.at);
            }

            printWriter.close();
            fileWriter.close();

            FileReader fileReader1 = new FileReader("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            StringBuilder inputBuffer = new StringBuilder();
            String str1;
            int counter1 = 0;
            while ((str1 = bufferedReader1.readLine()) != null && counter != 0) {
                String delims = "[|]";
                String[] tokens1 = str1.split(delims);
                if (tasks.get(counter1).isDone) {
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

            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }
        catch (IOException e)
        {
            dukePrint(e.getMessage());
        }
    }

    public static void startFile()
    {
        //Reading in the input from a previous session
        try
        {
            FileReader fileReader = new FileReader("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String str;
            while ((str = br.readLine()) != null) {
                String delims = "[|]";
                String[] tokens = str.split(delims);

                if (tokens[0].equals("T")) {

                    tasks.add(new Todo(tokens[2], "T"));
                    if (tokens[1].equals("true")) {
                        tasks.get(counter).isDone = true;
                    }
                    counter++;
                } else if (tokens[0].equals("D")) {
                    tasks.add(new Deadline(tokens[2], tokens[3], "D"));
                    if (tokens[1].equals("true")) {
                        tasks.get(counter).isDone = true;
                    }
                    counter++;
                } else if (tokens[0].equals("E")) {

                    tasks.add(new Event(tokens[2], tokens[3], "E"));
                    if (tokens[1].equals("true")) {
                        tasks.get(counter).isDone = true;
                    }
                    counter++;
                }
            }
            br.close();

        }
        catch (IOException | DukeException e) {
            dukePrint(e.getMessage());
        }
    }

}
