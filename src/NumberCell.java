/**
 * Created by nparker on 12/21/2015.
 */
public class NumberCell extends Cell {
    public NumberCell()
    {
        super("");
    }
    public NumberCell(String input)
    {
        super(input);
    }

    public String getInputValue()
    {
        String inputString = getInputString();
        try
        {
            Double.parseDouble(inputString);
        }
        catch (Exception e)
        {
            inputString = "";
        }
        return inputString;
    }
    public String getDisplayString()
    {
        String inputString = getInputString();
        try
        {
            Double.parseDouble(inputString);
        }
        catch (Exception e)
        {
            inputString = "";
        }

        if (inputString.length()>12)
        {
            inputString = inputString.substring(0, 11)+">";
        }
        else
        {
            int width = 12;
            int padSize = width-inputString.length();
            int padStart = inputString.length() + padSize /2;

            inputString = String.format("%" + padStart + "s",inputString);
            inputString = String.format("%-"+ width + "s",inputString);
        }

        return inputString;
    }

    public String getValue()
    {
        String inputString = getInputString();
        try
        {
            Double.parseDouble(inputString);
        }
        catch (Exception e)
        {
            inputString = "<empty>";
        }

        return inputString;
    }
}
