public abstract class Statement {
    /**Constants for periods*/
    public static final double YEAR = 365.25;
    public static final double QUARTER = 365.25/4.0;
    public static final double MONTH = 30.4375;
    public static final double WEEK = 7.0;
    public static final double DAY = 1.0;

    private boolean isIncome; //true for income, false for expense
    private String name;
    private String description;
    private double period; // uses above constants, or other
}