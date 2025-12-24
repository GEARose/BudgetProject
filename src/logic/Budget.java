package src.logic;

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
    public void add(Statement s) {statements.add(s);}
    /**
     * Removes and returns the first statement with a matching name
     * @param name - the statement name to remove
     * @return the removed statement, or null if not found
     */
    public Statement remove(String name) {
        int index=-1;
        for (int i=0; i<statements.size(); i++) {
            if (statements.get(i).getName().equals(name)) {index=i; break;}
        }
        if (index==-1) return null;
        return statements.remove(index);
    }
    /**
     * Creates a new Budget object that adjusts all of the statements
     * to the passed in period. ex: $200/month --> $2400/year
     * @param period - the period of the statements in the new budget
     * @return a new budget in which all statements have the given period
     */
    public Budget normal(double period) {
        Budget result = new Budget(name+"-Normal");
        for (Statement e: statements) {
            double ratio = period / e.getPeriod();
            if (e instanceof FixedStatement eF) {
                result.add(new FixedStatement(eF.isIncome(),eF.getName(),
                    eF.getDescription(),period,ratio*eF.getAmount()));
            }
            else if (e instanceof VariableStatement eV) {
                result.add(new VariableStatement(eV.isIncome,eV.getName(),
                    eV.getDescription(),period,ratio*eV.getLow(),ratio*eV.getHigh()));
            }
            else throw new IllegalArgumentException("Something went wrong.");
        }
        return result;
    }
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
    public double netAverage() {return inAverage()-outAverage();}
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
