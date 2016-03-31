/**
 * Created by nparker on 12/21/2015.
 */
public abstract class Cell
{
    private String inputString;

    public Cell ()
    {
        inputString = null;
    }

    public Cell (String input)
    {
        inputString = input;
    }

    public String getInputString()
    {
        return inputString;
    }

    public abstract String getInputValue();
    public abstract String getDisplayString();
    public abstract String getValue();
}
