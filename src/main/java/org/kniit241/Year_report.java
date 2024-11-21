package org.kniit241;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Year_report {
    ArrayList<Month> months;
    int expense;
    int not_expense;
    String year;

    public ArrayList<Month> getMonths() {
        return months;
    }

    public int getExpense() {
        return expense;
    }

    public int getNot_expense() {
        return not_expense;
    }

    public String getYear() {
        return year;
    }

    public Year_report(String year) throws IOException {
        this.year = year;
        months = new ArrayList<>();
        read_Year();
        this.expense = count_expense();
        this.not_expense = count_not_expense();
    }
    private void read_Year() throws IOException {
    String year_name = "y." + year + ".csv";
    List<String[]> rows = ReportReader.readFile("data/" + year_name, ",");

    for (int i = 0; i < rows.size(); i++) {
        String[] components = rows.get(i);
        if (components.length < 3) continue; // Пропускаем некорректные строки

        String month = components[0];
        int sum = Integer.parseInt(components[1]);
        boolean is_expense = Boolean.parseBoolean(components[2]);

        int expense = is_expense ? sum : 0;
        int not_expense = is_expense ? 0 : sum;

        // Если есть вторая строка для того же месяца
        if (i + 1 < rows.size()) {
            String[] nextComponents = rows.get(i + 1);
            if (nextComponents[0].equals(month)) {
                int sum2 = Integer.parseInt(nextComponents[1]);
                boolean is_expense2 = Boolean.parseBoolean(nextComponents[2]);

                if (is_expense2) {
                    expense += sum2;
                } else {
                    not_expense += sum2;
                }
                i++; // Пропускаем следующую строку, т.к. уже обработали
            }
        }

        Month year_month = new Month(year, month, expense, not_expense);
        months.add(year_month);
    }
}


    private int count_expense(){
        int count = 0;
        for(Month month : months){
            count+=month.getExpense();
        }
        return count;
    }
    private int count_not_expense(){
        int count = 0;
        for(Month month : months){
            count+=month.getNot_expense();
        }
        return count;
    }
    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        for (Month month: months)
        {
            stringBuilder.append(month.toString());
        }
        stringBuilder.append("Общие траты: ").append(expense).append(" ").append("Общие доходы: ").append(not_expense);
        return stringBuilder.toString();
    }
}
