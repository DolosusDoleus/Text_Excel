import java.text.ParseException;

/**
 * Created by nparker on 12/21/2015.
 */
public class FormulaCell extends Cell {

    private Spreadsheet sp;
    private boolean beenReferenced;

    public FormulaCell()
    {
        super("");
    }
    public FormulaCell(String input)
    {
        super(input);
    }
    public FormulaCell(String input, Spreadsheet sp)
    {
        super(input);
        this.sp = sp;
    }
    public String getInputValue()
    {
        String inputString = getInputString();
        if (inputString.length() == 0)
            {
                inputString  = "<empty>";
            }
        return inputString;
    }

    public String getDisplayString()
    {
        String inputString = getInputString();

        if (inputString.startsWith("("))
        {
            inputString = inputString.substring(1);
            if (inputString.endsWith(")"))
            {
                inputString = inputString.substring(0, inputString.length() - 1);
                inputString = inputString.trim();
            }
        }

        try
        {
            inputString = parseText(inputString);

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
        catch (Exception e)
        {
            return "   ERROR    ";
        }
    }

    public String getValue()
    {
        return parseText(getInputString());
    }

    public String parseText(String input)
    {
        try {

            if (input.startsWith("(")) {
                input = input.substring(1);
                if (input.endsWith(")")) {
                    input = input.substring(0, input.length() - 1);
                    input = input.trim();
                }
            }

            String[] inputArray = input.split(" ");

            if (inputArray[0].equals("sum")) {
                int colNum1 = Character.getNumericValue(inputArray[1].charAt(0)) - 10;
                int rowNum1 = Integer.parseInt(inputArray[1].substring(1, 2)) - 1;
                int colNum2 = Character.getNumericValue(inputArray[3].charAt(0)) - 10;
                int rowNum2 = Integer.parseInt(inputArray[3].substring(1, 2)) - 1;

                input = sp.sum(rowNum1, colNum1, rowNum2, colNum2) + "";
            } else if (inputArray[0].equals("avg")) {
                int colNum1 = Character.getNumericValue(inputArray[1].charAt(0)) - 10;
                int rowNum1 = Integer.parseInt(inputArray[1].substring(1, 2)) - 1;
                int colNum2 = Character.getNumericValue(inputArray[3].charAt(0)) - 10;
                int rowNum2 = Integer.parseInt(inputArray[3].substring(1, 2)) - 1;

                input = sp.avg(rowNum1, colNum1, rowNum2, colNum2) + "";
            } else {
                double total;
                double value;
                try {
                    int firstColNum = Character.getNumericValue(inputArray[0].charAt(0)) - 10;
                    int firstRowNum = Integer.parseInt(inputArray[0].substring(1, 2)) - 1;

                    total = Double.parseDouble(sp.getCellValue(firstRowNum, firstColNum));
                } catch (Exception e){
                    total = Double.parseDouble(inputArray[0]);
                }

                for (int i = 1; i < inputArray.length; i += 2) {
                    try {
                        int colNum = Character.getNumericValue(inputArray[i + 1].charAt(0)) - 10;
                        int rowNum = Integer.parseInt(inputArray[i + 1].substring(1, 2)) - 1;

                        value = Double.parseDouble(sp.getCellValue(rowNum, colNum));
                    }catch(Exception e){
                        value = Double.parseDouble(inputArray[i+1]);
                    }

                    switch (inputArray[i]) {
                        case "+":
                            total = total + value;
                            break;
                        case "-":
                            total = total - value;
                            break;
                        case "*":
                            total = total * value;
                            break;
                        case "/":
                            total = total / value;
                    }
                }

                input = Double.toString(total);
            }
        } catch (StackOverflowError e)
        {
            input = "ERROR";
        }
        return input;
    }
}
