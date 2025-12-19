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
     * Calculates the net (Income-Expenses), using lower bound amount for variable statements.  
     * @return the net dollar amount
     */
    public double netLow() {
        double result=0;
        for (Statement e: statements) {
            if (e instanceof FixedStatement eF) {
                if (eF.isIncome) result+= eF.getAmount();
                else result-= eF.getAmount();
            }
            else {
                VariableStatement eV = (VariableStatement) e;
                if (eV.isIncome) result+= eV.getLow();
                else result-= eV.getLow();
            }
        }
        return result;
    }
    /**
     * Calculates the net (Income-Expenses), using average amount for variable statements.  
     * @return the net dollar amount
     */
    public double netAverage() {
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
    /**
     * Calculates the net (Income-Expenses), using upper bound amount for variable statements.  
     * @return the net dollar amount
     */
    public double netHigh() {
        double result=0;
        for (Statement e: statements) {
            if (e instanceof FixedStatement eF) {
                if (eF.isIncome) result+= eF.getAmount();
                else result-= eF.getAmount();
            }
            else {
                VariableStatement eV = (VariableStatement) e;
                if (eV.isIncome) result+= eV.getHigh();
                else result-= eV.getHigh();
            }
        }
        return result;
    }
    /**
     * Calculates the total income, using lower bound amount for variable statements
     * @return the total dollar amount
     */
    public double inLow() {
        double result = 0;
        for (Statement e: statements) {
            if (e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getLow();
            }
        }
        return result;
    }
    /**
     * Calculates the total income, using average amount for variable statements
     * @return the total dollar amount
     */
    public double inAverage() {
        double result = 0;
        for (Statement e: statements) {
            if (e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getAverage();
            }
        }
        return result;
    }
    /**
     * Calculates the total income, using upper bound amount for variable statements
     * @return the total dollar amount
     */
    public double inHigh() {
        double result = 0;
        for (Statement e: statements) {
            if (e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getHigh();
            }
        }
        return result;
    }
    /**
     * Calculates the total expenses, using lower bound amount for variable statements
     * @return the total dollar amount
     */
    public double outLow() {
        double result = 0;
        for (Statement e: statements) {
            if (!e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (!e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getLow();
            }
        }
        return result;
    }
    /**
     * Calculates the total expenses, using average amount for variable statements
     * @return the total dollar amount
     */
    public double outAverage() {
        double result = 0;
        for (Statement e: statements) {
            if (!e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (!e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getAverage();
            }
        }
        return result;
    }
    /**
     * Calculates the total expenses, using upper bound amount for variable statements
     * @return the total dollar amount
     */
    public double outHigh() {
        double result = 0;
        for (Statement e: statements) {
            if (!e.isIncome && e instanceof FixedStatement eF) {
                result+=eF.getAmount();
            }
            if (!e.isIncome && e instanceof VariableStatement eV) {
                result+=eV.getHigh();
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
