import java.io.*;
import java.util.ArrayList;

public class Storage {

    protected String filepath;

    /**
     * Constructor to update the filepath variable
     *
     * @param Filepath
     */

    public Storage(String Filepath)
    {
        filepath = Filepath;
    }

    /**
     * Writes the contents of the task list to the data file when the program terminates
     *
     * @param tasks object containing the task list
     */

    public void exitFile(TaskList tasks) {

        // Update the .txt file at the end of the session

        try {
            FileReader fileReader1 = new FileReader(this.filepath);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            StringBuilder inputBuffer = new StringBuilder();
            String str1;
            int counter1 = 0;
            while ((str1 = bufferedReader1.readLine()) != null && tasks.size != 0) {
                String delims = "[|]";
                String[] tokens1 = str1.split(delims);
                if (tasks.task_list.get(counter1).isDone) {
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

            FileOutputStream fileOut = new FileOutputStream(this.filepath);
            fileOut.write(inputBuffer.toString().getBytes());
            fileOut.close();
        }
        catch (IOException e)
        {
            Duke.dukePrint(e.getMessage());
        }
    }

    /**
     * Reads the task list stored in the data file at the beginning of the program and updates the task list object
     *
     * @return object containing the task list
     */

    public ArrayList<Task> load(int counter)
    {
        //Reading in the input from a previous session

        ArrayList<Task> tasks = new ArrayList<>();

        try
        {
            FileReader fileReader = new FileReader(this.filepath);
            BufferedReader br = new BufferedReader(fileReader);
            String str;
            while ((str = br.readLine()) != null) {
                String delims = "[|]";
                String[] tokens = str.split(delims);

                switch (tokens[0]) {
                    case "T":
                        tasks.add(new Todo(tokens[2], "T"));
                        if (tokens[1].equals("true")) {
                            tasks.get(counter).isDone = true;
                        }
                        counter++;
                        break;
                    case "D":
                        tasks.add(new Deadline(tokens[2], tokens[3], "D"));
                        if (tokens[1].equals("true")) {
                            tasks.get(counter).isDone = true;
                        }
                        counter++;
                        break;
                    case "E":

                        tasks.add(new Event(tokens[2], tokens[3], "E"));
                        if (tokens[1].equals("true")) {
                            tasks.get(counter).isDone = true;
                        }
                        counter++;
                        break;
                }
            }
            br.close();
        }
        catch (IOException | DukeException e) {
            Duke.dukePrint(e.getMessage());
        }
        return tasks;
    }
}
