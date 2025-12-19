/**
 * An abstract class for income/expense statements.
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
    /** 
     * Changes the statement's type
     * @param isIncome - true if the statement is income, false if it is an expense
     */
    public void setType(boolean isIncome) {this.isIncome=isIncome;}
    public void setName(String name) {this.name=name;}
    public void setDescription(String desc) {description=desc;}
    /**
     * Changes the period for this statement
     * @param period - amount of time (in days) between each payment
     * Can use Statement.YEAR, Statement.QUARTER, Statement.MONTH,
     * Statement.WEEK, Statement.DAY, or just the number of days. 
     */
    public void setPeriod(double period) {this.period=period;}
}