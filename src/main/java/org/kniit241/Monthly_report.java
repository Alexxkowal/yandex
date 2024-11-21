package org.kniit241;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Monthly_report {
    private final String month;
    private final String year;
    private final ArrayList<Item> expense;
    private final ArrayList<Item> not_expense;
    private final int month_expense;
    private final int month_not_expense;

    public Monthly_report(String year, String month) throws IOException {
        this.year = year;
        this.month = month;
        this.expense = new ArrayList<>();
        this.not_expense = new ArrayList<>();
        read_month();
        this.month_expense = calculate_total(expense);
        this.month_not_expense = calculate_total(not_expense);
    }

    private void read_month() throws IOException {
    String month_name = "m." + year + month + ".csv";
    List<String[]> rows = ReportReader.readFile("data/" + month_name, ";");

    for (String[] components : rows) {
        if (components.length < 4) continue; // Пропускаем некорректные строки

        String name = components[0];
        boolean is_expense = Boolean.parseBoolean(components[1]);
        int quantity = Integer.parseInt(components[2]);
        int sum_of_one = Integer.parseInt(components[3]);

        Item item = new Item(name, sum_of_one, quantity, is_expense);
        if (is_expense) {
            expense.add(item);
        } else {
            not_expense.add(item);
        }
    }
}


    private int calculate_total(ArrayList<Item> items) {
        return items.stream().mapToInt(Item::getFull_price).sum();
    }

    public int getMonth_expense() {
        return month_expense;
    }

    public int getMonth_not_expense() {
        return month_not_expense;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("Месяц: " + month + "\nТраты:\n");
        for (Item item : expense) {
            str.append(item.toString()).append("\n");
        }
        str.append("Доходы:\n");
        for (Item item : not_expense) {
            str.append(item.toString()).append("\n");
        }
        return str.toString();
    }
}
