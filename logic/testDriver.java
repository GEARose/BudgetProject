public class testDriver {
    public static void main(String args[]) {
        // Income
        Statement workCheck = new FixedStatement(true,"Work","Biweekly Check",Statement.BIWEEKLY,400);
        Statement sideWork = new VariableStatement(true,"Side work",Statement.WEEK,0,400);
        
        // Expenses
        Statement rent = new FixedStatement(false,"rent",Statement.MONTH,800);
        Statement phone = new FixedStatement(false,"Phone bill",Statement.MONTH,60);

        Budget b1 = new Budget("Budget1");
        b1.addStatement(workCheck); b1.addStatement(sideWork);
        b1.addStatement(rent); b1.addStatement(phone);
        
        System.out.print(b1);
    }
}
