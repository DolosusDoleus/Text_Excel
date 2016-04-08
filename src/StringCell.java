/**
 * Created by nparker on 12/21/2015.
 */
public class StringCell extends Cell {
    public StringCell()
    {
        super("");
    }
    public StringCell(String input)
    {
        super(input);
    }

    public String getInputValue()
    {
        String inputString = getInputString();
        if (inputString.length() == 0)
        {
            inputString  = "";
        }
        return inputString;
    }

    public String getDisplayString()
    {
        String toDisplay = getInputString();

        if (toDisplay.startsWith("\""))
        {
            toDisplay = toDisplay.substring(1);
            if (toDisplay.endsWith("\""))
            {
                toDisplay = toDisplay.substring(0, toDisplay.length() - 1);
            }
        }

        if (toDisplay.length()>12)
        {
            toDisplay = toDisplay.substring(0, 11)+">";
        }
        else
        {
            int width = 12;
            int padSize = width-toDisplay.length();
            int padStart = toDisplay.length() + padSize /2;

            toDisplay = String.format("%" + padStart + "s",toDisplay);
            toDisplay = String.format("%-" + width + "s", toDisplay);
        }

        toDisplay.replaceAll("\"","\\\"");
        return toDisplay;
    }

    public String getValue()
    {
        String toDisplay = getInputString();

        toDisplay.replaceAll("\"","\\\"");
        return toDisplay;
    }

}
