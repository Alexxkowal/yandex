package org.kniit241;

public class Month {
    private final String year;
    private final String month;
    private final int expense;
    private final int not_expense;
    public Month(String year, String month, int expense, int not_expense) {
        this.year = year;
        this.month = month;
        this.expense = expense;
        this.not_expense = not_expense;
    }
    public String getYear() {
        return year;
    }

    public String getMonth() {
        return month;
    }

    public int getExpense() {
        return expense;
    }

    public int getNot_expense() {
        return not_expense;
    }
    @Override
    public String toString() {
        return month+" " + "траты: "+expense+" "+"доходы: "+not_expense +"\n";
    }


}
