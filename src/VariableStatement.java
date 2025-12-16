public class VariableStatement extends Statement {
    private double high;
    private double low;

    public VariableStatement(boolean type, String name, String desc, double period,
                            double high, double low) {
        isIncome=type; this.name=name; description=desc; this.period=period;
        this.high=high; this.low=low;
    }
}
