/** 
 * A source of income or an expense with a variable (non-fixed) amount
 */
public class VariableStatement extends Statement {
    private double low;
    private double high;
    private double average;

    /**
     * Creates a new VariableStatement object with a description
     * @param type - true if income, false if expense
     * @param name - name for the statement
     * @param desc - description for the statement
     * @param period - period in days
     * @param low - estimate of the lowest dollar amount paid per period
     * @param high - estimate of the highest dollar amount paid per period
     */
    public VariableStatement(boolean type, String name, String desc, double period,
                            double low, double high) {
        isIncome=type; this.name=name; description=desc; this.period=period;
        this.high=high; this.low=low; average=(low+high)/2.0;
    }
    /**
     * Creates a new VariableStatement object without a description
     * @param type - true if income, false if expense
     * @param name - name for the statement
     * @param period - period in days
     * @param high - estimate of the highest dollar amount paid per period
     * @param low - estimate of the lowest dollar amount paid per period
     */
    public VariableStatement(boolean type, String name, double period,
                            double low, double high) {
        this(type,name,"",period,low,high);
    }
    /**
     * Creates a new VariableStatement object by copying another
     * @param other - the object to be copied
     */
    public VariableStatement(VariableStatement other) {
        this(other.isIncome,other.name,other.description,other.period,other.low,other.high);
    }

    public double getLow() {return low;}
    public double getHigh() {return high;}
    public double getAverage() {return average;}

    public void setLow(double low) {this.low=low;}
    public void setHigh(double high) {this.high=high;}
    public void setAverage(double average) {this.average=average;}

    @Override
    /**
     * Creates a readable representation of the Statement
     * @return a formatted String
     */
    public String toString() {
        return "\n"+name+":\n"
        +"\t$"+low+"-"+high
        +"\tevery "+period+" days"
        +"\t"+description;
    }
}
