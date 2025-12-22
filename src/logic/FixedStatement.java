package src.logic;
/** 
 * A source of income or an expense with a fixed (non-variable) amount
 */
public class FixedStatement extends Statement {
    /** The fixed dollar amount spent/recieved each period */
    private double amount;

    /**
     * Creates a new FixedStatement object with a description
     * @param type - true if income, false if expense
     * @param name - name for the statement
     * @param desc - description for the statement
     * @param period - period in days
     * @param amount - dollar amount paid per period
     */
    public FixedStatement(boolean type, String name, String desc, double period, double amount) {
        isIncome=type; this.name=name; description=desc;
        this.period=period; this.amount=amount;
    }
    /**
     * Creates a new FixedStatement object without a descripton
     * @param type - true if income, false if expense
     * @param name - name for the statement
     * @param period - period in days
     * @param amount - dollar amount paid per period
     */
    public FixedStatement(boolean type, String name, double period, double amount) {
        this(type,name,"[no description]",period, amount);
    }
    /**
     * Creates a new FixedStatement object by copying another
     * @param other - the object to be copied
     */
    public FixedStatement(FixedStatement other) {
        this(other.isIncome,other.name,other.description,other.period,other.amount);
    }
    
    public double getAmount() {return amount;}
    public void setAmount(double amount) {this.amount=amount;}

    @Override
    /**
     * Creates a readable representation of the Statement
     * @return a formatted String
     */
    public String toString() {
        String sign = " - ";
        if (isIncome) sign=" + ";
        return "\n"+sign+name+":\n"
        +"\t$"+amount+"\n"
        +"\tevery "+period+" days"+"\n"
        +"\t"+description;
    }
}
