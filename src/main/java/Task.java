public class Task {
    public String by;
    public String at;
    protected String description;
    protected boolean isDone;
    protected String type;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.type = "";
    }

    public String getStatusIcon() {
        return (this.isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return "\t"+ "[" + this.getStatusIcon()+ "] "+ this.description ;
    }

    public void markAsDone()
    {
        this.isDone = true;
    }
}