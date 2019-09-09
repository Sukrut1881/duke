public class Task {
    public String by;
    public String at;
    protected String description;
    protected boolean isDone;
    protected String type;

    /**
     * Constructor instantiate the description, isDone and type variables when a new Task object
     *
     * @param description string containing the task description
     */

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    /**
     * Returns tick or X symbols
     *
     * @return returns the valid symbol
     */

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Returns the task description and status icon to the various task classes
     *
     * @return the string in the specific format
     */

    public String toString() {
        return "\t"+ "[" + this.getStatusIcon()+ "] "+ this.description ;
    }

    /**
     * Method to set the isDone of the current object to true indicating the task is done
     *
     */

    public void markAsDone()
    {
        this.isDone = true;
    }
}