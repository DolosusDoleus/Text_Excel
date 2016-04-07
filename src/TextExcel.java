/**
 * Created by nparker on 12/21/2015.
 */
import java.util.Scanner;

//EXTRA CREDIT:
//Sorts rectangular ranges
//DateCell class
//Outputs ERROR when a FormulaCell references a StringCell or an empty cell.
public class TextExcel
{
    public static void main (String[] args)
    {
        boolean continueCode = true;
        int rowNum;
        int colNum;
        String input;
        String[] inputArray;
        String value;

        Scanner sc = new Scanner(System.in);

        Spreadsheet sp = new Spreadsheet();
        sp.init();

        while (continueCode)
        {
            System.out.println("Enter an operation:");
            input = sc.nextLine();

            if (input.toLowerCase().equals("print"))
                sp.print();
            else if (input.toLowerCase().equals("exit"))
                break;
            else if (input.toLowerCase().contains("clear"))
            {
                if (input.toLowerCase().equals("clear"))
                    sp.clearAll();
                else{
                    inputArray = input.split(" ");
                    try{
                        int colNum1 = ((int)inputArray[1].charAt(0))-65;
                        int rowNum1 = Integer.parseInt(String.valueOf(inputArray[1].charAt(1)))-1;
                        int colNum2 = ((int)inputArray[3].charAt(0))-65;
                        int rowNum2 = Integer.parseInt(String.valueOf(inputArray[3].charAt(1)))-1;
                        sp.clearRange(rowNum1,colNum1,rowNum2,colNum2);
                    }catch(Exception e){
                        colNum = ((int)inputArray[1].charAt(0))-65;
                        rowNum = Integer.parseInt(String.valueOf(inputArray[1].charAt(1)))-1;
                        sp.clearCell(rowNum,colNum);
                    }
                }
            }
            else if(input.toLowerCase().contains("sort")){
                inputArray = input.split(" ");
                int colNum1 = ((int)inputArray[1].charAt(0))-65;
                int rowNum1 = Integer.parseInt(String.valueOf(inputArray[1].charAt(1)))-1;
                int colNum2 = ((int)inputArray[3].charAt(0))-65;
                int rowNum2 = Integer.parseInt(String.valueOf(inputArray[3].charAt(1)))-1;
                if(inputArray[0].toLowerCase().equals("sorta")){
                    sp.sortA(rowNum1,colNum1,rowNum2,colNum2);
                }
                else{
                    sp.sortD(rowNum1,colNum1,rowNum2,colNum2);
                }
            }
            else if (input.contains("\""))
            {
                inputArray = input.split("=");
                inputArray[0]=inputArray[0].trim();
                inputArray[1]=inputArray[1].trim();
                colNum = ((int)inputArray[0].charAt(0))-65;
                rowNum = Integer.parseInt(String.valueOf(inputArray[0].charAt(1)))-1;
                value = inputArray[1];
                sp.setCell(rowNum,colNum,new StringCell(value));
            }
            else if (input.contains("(") && input.contains(")"))
            {
                inputArray = input.split("\\(");
                String[] inputArray2 = inputArray[0].split(" ");
                colNum = ((int)inputArray2[0].charAt(0))-65;
                rowNum = Integer.parseInt(String.valueOf(inputArray2[0].charAt(1)))-1;
                value = "("+inputArray[1];
                sp.setCell(rowNum,colNum,new FormulaCell(value,sp));
            }
            else if (input.contains("="))
            {
                inputArray = input.split(" ");
                colNum = ((int)inputArray[0].charAt(0))-65;
                rowNum = Integer.parseInt(String.valueOf(inputArray[0].charAt(1)))-1;
                value = inputArray[2];
                sp.setCell(rowNum, colNum, new NumberCell(value));
            }
            else
            {
                colNum = ((int)input.charAt(0))-65;
                rowNum = Integer.parseInt(String.valueOf(input.charAt(1)))-1;
                System.out.println(input+" = "+sp.getInputValue(rowNum,colNum));
            }
        }

        System.out.println("PROGRAM END");

        /* Test Case 1
        A1 = 900
        B1 = 123.321
        A2 = -54
        B2 = 22.7
        print
        C3 = ( sum A1 B2 )
        D3 = ( avg A1 B2 )
        A5 = ( A1 + B2 )
        B5 = ( A1 - B2 )
        C5 = ( A1 * B2 )
        D5 = ( A1 / B2 )
        print
        clear
        exit
        */

        /* Test Case 2
        A1 = 76.4
        B1 = 43
        C1 = -5
        A2 = 141.22
        B2 = -123
        C2 = 54.33
        print
        sortD A1 C2
        print
        exit
        */

        /* Test Case 3
        print
        exit
        */

        /* Test Case 4
        A1 = 1
        B1 = 2
        A2 = 3
        B2 = 4
        C3 = ( A1 + A2 + B2 )
        print
        sortD A1 B2
        print
        exit
        */

        /* Test Case 5
        A1 = 1
        B1 = 2
        A2 = 3
        B2 = 4
        C1 = ( sum A1 B2 )
        C3 = ( C1 - A1 )
        print
        sortD A1 B2
        print
        exit
        */

        /* Test Case 6
        A1 = 11
        B1 = ( A1 + C1 )
        C1 = ( A1 + B1 )
        print
        exit
        */

        /* Test Case 7
        A1 = "Lorem ipsum dolor sit amet"
        B1 = "Quot"es"
        A2 = "12/22/1999"
        B2 = 9999999999999999999999999999999999
        print
        exit
        */
    }
}

