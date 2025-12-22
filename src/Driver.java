package src;
import src.logic.*;
import java.util.List;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver {
    public static List<Budget> budgets = new ArrayList<>();
    public static void main(String args[]) {
        int choice;

        for (;;) {
            displayBudgets();
            choice = getIntInput("Choose by typing a number:",-1,budgets.size());
            if (choice==-1) break; // exit
            else if (choice==0) { // make a new Budget
                String name = getStringInput("Name the new Budget:");
                budgets.add(new Budget(name));
            } 
            else { //choose a budget
                for (;;) {
                    Budget boi = budgets.get(choice-1); // budget of interest
                    displayBudgetOptions();
                    choice = getIntInput("Choose by typing a number:", -1, 1);
                    if (choice==-1) break; // back
                    else if (choice==0) System.out.println(boi); // display statements
                    else if (choice==1) { // add a new statement
                        String name = getStringInput("Name the new Statement");
                        String desc = getStringInput("Write a brief description, or hit enter to leave blank");

                        boolean isIncome;
                        choice = getIntInput("Is your statement:\n 0. an expense\n 1. income", 0, 1);
                        if (choice==1) isIncome=true;
                        else isIncome=false;

                        // TODO: get the period

                        // fixed or variable
                        choice=getIntInput("Is your statement:\n 0. fixed\n 1. variable", 0, 1);
                        if (choice==0) { // fixed

                        }
                        else { // variable

                        }
                    }
                }
            }
        }
        System.out.println("Bye!");
    }
    public static void displayBudgetOptions() {
        System.out.println("-1. Back\n0. Display Statements");
        System.out.println("1. Add a new Statement");
    }
    public static void displayBudgets() {
        System.out.println("-1. Exit program\n0. Create new Budget");
        for (int i=0; i<budgets.size(); i++) {
            System.out.println(i+1+". "+budgets.get(i).getName());
        }
    }
    public static String getStringInput(String prompt) {
        System.out.println(prompt);
        Scanner in = new Scanner(System.in);
        String result=in.nextLine();
        return result;
    }
    public static int getIntInput(String prompt, int low, int high) {
        Scanner in = new Scanner(System.in);
        System.out.println(prompt);
        int value=-1;
        boolean isValid = false;
        do {
            try {
                value = in.nextInt();
                if (low<=value && value<=high) isValid=true;
                else System.out.println("Value must be between "+low+" and "+high);
            } catch (InputMismatchException e) {
                System.out.println("Try again");
                in.nextLine();
            }
        } while (!isValid);
        return value;
    }
}
