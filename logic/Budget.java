import java.util.List;
import java.util.ArrayList;
/**
 * A collection of Statement objects.
 * Includes utility methods.
 */
public class Budget {
    private String name;
    private List<Statement> statements;

    /**
     * Creates a new empty Budget object
     * @param name - the budget name
     */
    public Budget(String name) {
        statements = new ArrayList<>();
        this.name=name;
    }
    /**
     * Creates a new Budget object by copying another
     * @param other - the object to be copied
     */
    public Budget(Budget other) {
        this(other.name);
        for (Statement e: other.statements) {
            Statement copy;
            if (e instanceof FixedStatement eF) {
                copy = new FixedStatement(eF);
            }
            if (e instanceof VariableStatement eV) {
                copy = new VariableStatement(eV);
            }
            else throw new IllegalArgumentException("Statement is neither FixedStatement nor VariableStatement");
            statements.add(copy);
        }
    }

    /**
     * Adds a statement to the budget
     * @param s - the statement to be added
     */
    public void addStatement(Statement s) {statements.add(s);}
    /**
     * Calculates the net (Income-Expenses), using average amount for variable statements.  
     * @return the net dollar amount
     */
    public double net() {
        double result=0;
        for (Statement e: statements) {
            if (e instanceof FixedStatement eF) {
                if (eF.isIncome) result+= eF.getAmount();
                else result-= eF.getAmount();
            }
            else {
                VariableStatement eV = (VariableStatement) e;
                if (eV.isIncome) result+= eV.getAverage();
                else result-= eV.getAverage();
            }
        }
        return result;
    }

    public String getName() {return name;}

    @Override
    /**
     * Creates a readable representation of the Budget
     * @return a formatted String
     */
    public String toString() {
        String result = "\n===================================================\n";
        result+=name+"::\n";
        result+="-----------------------------------";
        for (Statement e: statements) {
            result+=e.toString();
        }
        return result;
    }
}
