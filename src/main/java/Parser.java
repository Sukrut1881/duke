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

    public Parser()
    {

    }
    public String inputString()
    {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }

    public String[] tokenize (String input1)
    {
        StringTokenizer st = new StringTokenizer(input1);
        int j = 0;
        String[] token = new String[100];
        while (st.hasMoreTokens()) {
            token[j] = st.nextToken();
            j++;
        }
        return token;
    }

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
