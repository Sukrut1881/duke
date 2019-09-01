import java.io.*;
import java.util.ArrayList;

public class TaskList {

    ArrayList <Task> task_list;
    int size;

    TaskList(ArrayList<Task> AL) throws DukeException {
        if(AL.size() == 101)
        {
        throw new DukeException("\n    ____________________________________________________________\n" +
                "     ☹ OOPS!!! The list is full.\n" +
                "    ____________________________________________________________\n");
        }
        task_list = AL;
        size = AL.size();
    }

    TaskList() {
        task_list = new ArrayList<>();
    }

    public static void deleteTask(String description, TaskList tasks) {

        // Update the .txt file when the thing is deleted

        try {
            FileReader fileReader1 = new FileReader("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            StringBuilder inputBuffer = new StringBuilder();
            String str1;
            while ((str1 = bufferedReader1.readLine()) != null && tasks.size !=0) {
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
            Duke.dukePrint(e.getMessage());
        }
    }

    public static void newTask(Task task) throws IOException {
        FileWriter fileWriter = new FileWriter("C:\\Users\\thesu\\DukeCS2113\\duke\\src\\data\\duke.txt", true);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        if(task.type == "E") {
            printWriter.println(task.type + "|" + task.isDone + "|" + task.description + "|" + task.at);
        }
        else if (task.type == "D")
        {
            printWriter.println(task.type + "|" + task.isDone + "|" + task.description + "|" + task.by);
        }
        else
        {
            printWriter.println(task.type + "|" + task.isDone + "|" + task.description);
        }

        printWriter.close();
        fileWriter.close();
    }

}
