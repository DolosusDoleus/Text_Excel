/**
 * Created by nparker on 12/21/2015.
 */
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCell extends Cell
{
    private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public DateCell()
    {
        super("");
    }
    public DateCell(String input)
    {
        super(input);
    }

    public String getInputValue()
    {
        Date date;
        String displayString;
        try
        {
            date = dateFormat.parse(getInputString());
            displayString = dateFormat.format(date);
        }
        catch(ParseException e)
        {
            displayString = "<empty>";
        }
        return displayString;
    }

    public String getDisplayString()
    {
        Date date;
        String displayString;
        try
        {
            date = dateFormat.parse(getInputString());
            displayString = dateFormat.format(date);
        }
        catch(ParseException e)
        {
            displayString = "";
        }

        if (displayString.length()>12)
        {
            displayString = displayString.substring(0, 10)+">";
        }
        else
        {
            int width = 12;
            int padSize = width-displayString.length();
            int padStart = displayString.length() + padSize /2;

            displayString = String.format("%" + padStart + "s",displayString);
            displayString = String.format("%-"+ width + "s",displayString);
        }

        return displayString;
    }

    public String getValue()
    {
        Date date;
        String displayString;
        try
        {
            date = dateFormat.parse(getInputString());
            displayString = dateFormat.format(date);
        }
        catch(ParseException e)
        {
            displayString = "";
        }

        return displayString;
    }

}
