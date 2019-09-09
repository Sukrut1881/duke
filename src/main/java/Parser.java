import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Parser {

    private static  SimpleDateFormat formatter_st = new SimpleDateFormat("dd 'st' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_nd = new SimpleDateFormat("dd 'nd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_rd = new SimpleDateFormat("dd 'rd' 'of' MMMMMMMMMMMM yyyy, h:mm a");
    private static  SimpleDateFormat formatter_th = new SimpleDateFormat("dd 'th' 'of' MMMMMMMMMMMM yyyy, h:mm a");

    /**
     * Creates a new Scanner object which takes in the next line of input
     *
     * @return String containing the next line input by the user
     */

    public String inputString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    /**
     * Takes in the input string to tokenize the string into individual words
     *
     * @param input the command entered by the user
     * @return String[] array of the various tokens in the input string
     */

    public String[] tokenize (String input)
    {
        StringTokenizer st = new StringTokenizer(input);
        int j = 0;
        String[] token = new String[100];
        while (st.hasMoreTokens()) {
            token[j] = st.nextToken();
            j++;
        }
        return token;
    }

    /**
     * Validates if the entered Task end-time is a date and converts the date into the Duke specific format
     *
     * @param day String containing the date of the Task
     * @return String of the date converted according to the relevant
     * @throws ParseException exception thrown when the input format of the date is incorrect
     */

    static String detectDate(String day) throws ParseException {

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

        } else {
            return day;
        }
        return output;
    }

}
