package src;
import src.logic.*;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;

public class Driver implements Serializable {
    public static List<Budget> budgets = new ArrayList<>();
    public static final String sep = "===========================================";
    public static transient Scanner in = new Scanner(System.in);
    public static void main(String args[]) throws SomethingWentWrongException {
        int choice;
        for (;;) { 
            displayBudgets();
            choice = getIntInput("Choose by typing a number: ",-1,budgets.size()+2);
            int choice1=choice;
            if (choice==-1) break; // exit
            else if (choice==0) { // make a new Budget
                String name = getStringInput("Name the new Budget: ");
                budgets.add(new Budget(name));
            }
            else if (choice==1) { // save
                System.out.println("Saving...");
                try (FileOutputStream f = new FileOutputStream("BudgetSave.txt");
                    ObjectOutputStream out = new ObjectOutputStream(f);) {
                    writeToFile(out);
                } catch (IOException e) {e.printStackTrace();}
                System.out.println("Successfully Saved.");
            }
            else if (choice==2) { // load
                System.out.println("Loading...");
                try {
                    budgets = readFile("BudgetSave.txt");
                    System.out.println("Successfully Loaded");
                } catch (IOException e) {
                    System.err.println("File not found!");
                }                
            }
            else for (;;) { //choose a budget
                Budget boi = budgets.get(choice1 - 3); // budget of interest
                displayBudgetOptions();
                choice = getIntInput("Choose by typing a number: ", -1, 3);
                if (choice==-1) break; // back
                else if (choice==0) System.out.println(boi); // display statements
                else if (choice==1) { // add a new statement
                    String name = getStringInput("Name the new Statement: ");
                    String desc = getStringInput("Write a brief description, or hit enter to leave blank: ");

                    boolean isIncome;
                    choice = getIntInput("Is your statement:\n -1. back\n 0. an expense\n 1. income\n", -1, 1);
                    if (choice==-1) break;
                    if (choice==1) isIncome=true;
                    else isIncome=false;

                    double period;
                    System.out.println("-1. back\n0. custom\n1. Day\n2. Week\n3. Biweekly\n4. Month");
                    System.out.println("5. Quarter\n6. Year");
                    choice = getIntInput("Choose the period of the statement: ", -1, 6);
                    if (choice==-1) break;
                    else if (choice==0) { // custom
                        period=getDoubleInput("Enter the period as a number of days: ", 1, 36525);
                    }
                    else if (choice==1) period=Statement.DAY;
                    else if (choice==2) period=Statement.WEEK;
                    else if (choice==3) period=Statement.BIWEEKLY;
                    else if (choice==4) period=Statement.MONTH;
                    else if (choice==5) period=Statement.QUARTER;
                    else if (choice==6) period=Statement.YEAR;
                    else throw new SomethingWentWrongException("idk");
                    

                    // fixed or variable
                    choice=getIntInput("Is your statement:\n 0. fixed\n 1. variable\n", 0, 1);
                    Statement newStatement;
                    if (choice==0) { // fixed
                        double amount = getDoubleInput("Enter the amount paid every period: ", 0, 100_000_000_000_000.0);
                        newStatement = new FixedStatement(isIncome,name,desc,period,amount);
                    }
                    else { // variable
                        double low = getDoubleInput("Enter an estimate for the lower bound of the amount paid per period: ", 0, 100_000_000_000_000.0);
                        double high= getDoubleInput("Enter an estimate for the upper bound: ", low,100_000_000_000_000.0);
                        newStatement = new VariableStatement(isIncome,name,desc,period,low,high);
                    }
                    boi.add(newStatement);
                    System.out.println("Statement successfully created!");
                    System.out.println(newStatement);
                }
                else if (choice==2) { // edit a statement
                    // TODO
                    System.out.println("Coming soon...");
                }
                else if (choice==3) { // delete a statement
                    String name = getStringInput("Name of the statement to delete: ");
                    if (deleteStatement(boi, name)) System.out.println("Successfullly Deleted");
                    else System.out.println("Not found");
                }
            }
            
        }
        System.out.println("Bye!");
        in.close();
    }
    private static void writeToFile(ObjectOutputStream out) throws IOException {
        out.writeObject(budgets);
        out.close();
    }
    private static List<Budget> readFile(String fileName) throws IOException {
        try (FileInputStream in = new FileInputStream(fileName);
            ObjectInputStream o = new ObjectInputStream(in)) {
                @SuppressWarnings("unchecked")
                List<Budget> l = (ArrayList<Budget>) o.readObject();
                return l;
        } catch (ClassNotFoundException e) {e.printStackTrace();}
        return null;
    }
    public static boolean deleteStatement(Budget b,String name) {
        Statement s = b.remove(name);
        if (s==null) return false;
        return true;
    }
    public static void displayBudgetOptions() {
        System.out.println(sep+"\n-1. Back\n0. Display Statements");
        System.out.println("1. Add a new Statement\n2. Edit a Statement");
        System.out.println("3. Delete a Statement");
    }
    public static void displayBudgets() {
        System.out.println(sep+"\n-1. Exit program\n0. Create new Budget");
        System.out.println("1. Save\n2. Load");
        for (int i=0; i<budgets.size(); i++) {
            System.out.println(i+3+". "+budgets.get(i).getName());
        }
    }
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        String result=in.nextLine();
        return result;
    }
    public static int getIntInput(String prompt, int low, int high) {
        System.out.print(prompt);
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
        in.nextLine();
        return value;
    }
    public static double getDoubleInput(String prompt, double low, double high) {
        System.out.print(prompt);
        double value=-1.0;
        boolean isValid = false;
        do {
            try {
                value = in.nextDouble();
                if (low<=value && value<=high) isValid=true;
                else System.out.println("Value must be between "+low+" and "+high);
            } catch (InputMismatchException e) {
                System.out.println("Try again");
                in.nextLine();
            }
        } while (!isValid);
        in.nextLine();
        return value;
    }
}