/**
 * Created by nparker on 12/24/2015.
 */
public class VoidCell extends Cell {

    public VoidCell()
    {
        super("");
    }

    public VoidCell(String input)
    {
        super(input);
    }

    public String getInputValue()
    {
        return "<empty>";
    }

    public String getDisplayString()
    {
        return "            ";
    }

    public String getValue()
    {
        return "<empty>";
    }
}
