/**
 * An abstract class for income/expense statements
 * FixedStatement and VariableStatement inherit from this class
 */
public abstract class Statement {
    /**Constants for periods*/
    public static final double YEAR = 365.25;
    public static final double QUARTER = 365.25/4.0;
    public static final double MONTH = 30.4375;
    public static final double WEEK = 7.0;
    public static final double DAY = 1.0;

    protected boolean isIncome; //true for income, false for expense
    protected String name;
    protected String description;
    protected double period; // uses above constants, or other

    public boolean isIncome() {return isIncome;}
    public String getName() {return name;}
    public String getDescription() {return description;}
    public double getPeriod() {return period;}

}