/**
 * Created by nparker on 12/21/2015.
 */

public class Spreadsheet
{
    //Edit these to change the size of the spreadsheet
    public final int NUM_OF_ROWS = 10;
    public final int NUM_OF_COLUMNS = 7;

    public Cell[][] cells = new Cell[NUM_OF_ROWS][NUM_OF_COLUMNS];

    //Spreadsheet constructor
    public Spreadsheet()
    {

    }

    //Creates the spreadsheet
    public void init()
    {
        for (int i=0;i<NUM_OF_ROWS;i++)
        {
            for (int j=0;j<NUM_OF_COLUMNS;j++)
            {
                cells[i][j] = new VoidCell();
            }
        }
    }

    //Returns the specified cell
    public Cell getCell(int rowNum, int colNum)
    {
        return cells[rowNum][colNum];
    }

    //Returns the actual value of a cell
    public String getCellValue(int rowNum, int colNum)
    {
        return cells[rowNum][colNum].getValue();
    }

    //Returns the inputString of a cell
    public String getInputValue(int rowNum, int colNum) {
        return cells[rowNum][colNum].getInputValue();
    }

    //Sets the value of a cell
    public void setCell(int rowNum, int colNum, Cell newCell)
    {
        cells[rowNum][colNum] = newCell;
    }

    //Adds together all number values in a certain range
    public double sum (int rowNum1, int colNum1, int rowNum2, int colNum2)
    {
        double sum = 0;

        for (int i=rowNum1;i<=rowNum2;i++)
        {
            for (int j=colNum1;j<=colNum2;j++)
            {
                try
                {
                    sum += Double.parseDouble(cells[i][j].getValue());
                }
                catch (Exception e)
                {
                    return -123454321.12345678987654321234567898765543212345678987654321;
                }
            }
        }

        return sum;
    }

    //Takes in number values in a specified range and returns their average.
    public double avg (int rowNum1, int colNum1, int rowNum2, int colNum2)
    {
        double sum = 0;
        int count = 0;

        for (int i=rowNum1;i<=rowNum2;i++)
        {
            for (int j=colNum1;j<=colNum2;j++)
            {
                try
                {
                    sum += Double.parseDouble(cells[i][j].getValue());
                    count++;
                }
                catch (Exception e)
                {
                    return -123454321.12345678987654321234567898765543212345678987654321;
                }
            }
        }

        return sum/count;
    }

    //Sorts cells from least to greatest
    public void sortA(int rowNum1, int colNum1,int rowNum2, int colNum2)
    {
        Cell temp;

        for (int i=rowNum1;i<=rowNum2;i++)
        {
            for (int j=colNum1;j<=colNum2;j++)
            {
                for (int k=rowNum1;k<=rowNum2;k++)
                {
                    for (int l=colNum1;l<=colNum2;l++)
                    {
                        if (Double.parseDouble(cells[k][l].getInputValue()) > Double.parseDouble(cells[i][j].getInputValue()))
                        {
                            temp = cells[i][j];
                            cells[i][j] = cells[k][l];
                            cells[k][l] = temp;
                        }
                    }
                }
            }
        }
    }

    //Sorts cells from greatest to least
    public void sortD(int rowNum1, int colNum1,int rowNum2, int colNum2)
    {
        Cell temp;

        for (int i=rowNum1;i<=rowNum2;i++)
        {
            for (int j=colNum1;j<=colNum2;j++)
            {
                for (int k=rowNum1;k<=rowNum2;k++)
                {
                    for (int l=colNum1;l<=colNum2;l++)
                    {
                        if (Double.parseDouble(cells[k][l].getInputValue()) < Double.parseDouble(cells[i][j].getInputValue()))
                        {
                            temp = cells[i][j];
                            cells[i][j] = cells[k][l];
                            cells[k][l] = temp;
                        }
                    }
                }
            }
        }
    }

    //Deletes the value from the specified cell
    public void clearCell(int rowNum, int colNum)
    {
        cells[rowNum][colNum] = new VoidCell();
    }

    //Deletes values in a specified range
    public void clearRange(int rowNum1, int colNum1, int rowNum2, int colNum2)
    {
        for (int i=rowNum1;i<=rowNum2;i++)
        {
            for (int j=colNum1;j<=colNum2;j++)
            {
                cells[i][j] = new VoidCell();
            }
        }
    }

    //Deletes values from all cells.
    public void clearAll()
    {
        for (int i=0;i<NUM_OF_ROWS;i++)
        {
            for (int j=0;j<NUM_OF_COLUMNS;j++)
            {
                cells[i][j] = new VoidCell();
            }
        }
    }

    //Prints the spreadsheet, defined by NUM_OF_COLUMNS and NUM_OF_ROWS
    public void print()
    {
        System.out.print("            |");

        for (int i=0;i<NUM_OF_COLUMNS;i++)
        {
            if (i<NUM_OF_COLUMNS-1)
            {
                System.out.print("     "+(char)(i+65)+"      "+"|");
            }
            else
            {
                System.out.println("     "+(char)(i+65)+"      "+"|");
            }
        }
        System.out.println(new String(new char[NUM_OF_COLUMNS+1]).replace("\0", "~~~~~~~~~~~~+"));
        for (int i=0;i<NUM_OF_ROWS;i++)
        {
            if (i<9)
            {
                System.out.print("     " + (i + 1) + "      |");

                for (int j=0;j<NUM_OF_COLUMNS;j++)
                {
                    if (j<NUM_OF_COLUMNS-1)
                    {
                        System.out.print(cells[i][j].getDisplayString()+"|");
                    }
                    else
                    {
                        System.out.println(cells[i][j].getDisplayString()+"|");
                    }
                }
                System.out.println(new String(new char[NUM_OF_COLUMNS+1]).replace("\0", "~~~~~~~~~~~~+"));
            }
            else
            {
                System.out.print("     " + (i + 1) + "     |");

                for (int j=0;j<NUM_OF_COLUMNS;j++)
                {
                    if (j<NUM_OF_COLUMNS-1)
                    {
                        System.out.print(cells[i][j].getDisplayString()+"|");
                    }
                    else
                    {
                        System.out.println(cells[i][j].getDisplayString()+"|");
                    }
                }
                System.out.println(new String(new char[NUM_OF_COLUMNS+1]).replace("\0", "~~~~~~~~~~~~+"));
            }
        }
    }

}
