package utility;

import java.util.ArrayList;
import java.util.Scanner;

public class TextUI {


    private static Scanner sc = new Scanner(System.in);

    public ArrayList<String> promptChoice( ArrayList<String> options, int limit, String msg){
        displayMsg(msg);
        displayList(options, "");
        ArrayList<String> choices = new ArrayList<>();  //Lave en beholder til at gemme brugerens valg

        while(choices.size() < limit){             //tjekke om brugeren skal vælge igen

            int choice = promptNumeric(msg);
            choices.add(options.get(choice-1));
        }
        return choices;
    }

    public void displayList(ArrayList<String>list, String msg) {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1+". "+list.get(i));
        }
    }

    public void displayMsg(String msg){
        System.out.println(msg);

    }

    public int promptNumeric(String msg){
        int num = -1;

        while (num < 0)
        {
            System.out.print(msg + " ");
            String input = sc.nextLine();

            try
            {
                // Try converting the input to an integer
                num = Integer.parseInt(input);

                // Check if the number is non-negative
                if (num < 0)
                {
                    System.err.println("Please enter a non-negative number.");
                }
            }
            catch (NumberFormatException e)
            {
                // If input wasn't a valid number
                System.err.println("Invalid input, enter a valid number.");
            }
        }

        return num; // Return the valid number
    }

    public String promptText(String msg){
        displayMsg(msg);         //Stille brugeren et spørgsmål
        String input = sc.nextLine();          //Give brugere et sted at placere sit svar og vente på svaret

        return input;
    }

    public boolean promptBinary(String msg){
        displayMsg(msg);
        String input = sc.nextLine();
        if(input.equalsIgnoreCase("Y")){
            return true;
        }
        else if(input.equalsIgnoreCase("N")){
            return false;
        }
        else{
            return promptBinary(msg);
        }
    }
}
