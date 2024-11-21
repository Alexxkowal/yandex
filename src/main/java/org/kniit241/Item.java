package org.kniit241;

public class Item {
    private final String name;
    private final int sum_of_one;
    private final int quantity;
    private final boolean is_expense;
    private final int full_price;

    public Item(String name, int sum_of_one, int quantity, boolean is_expense) {
        this.name = name;
        this.sum_of_one = sum_of_one;
        this.quantity = quantity;
        this.is_expense = is_expense;
        this.full_price = sum_of_one*quantity;
    }

    @Override
    public String toString() {
        return name+" "+ sum_of_one+" "+ quantity+" "+is_expense;
    }

    public String getName() {
        return name;
    }

    public int getSum_of_one() {
        return sum_of_one;
    }

    public int getQuantity() {
        return quantity;
    }

    public boolean isIs_expense() {
        return is_expense;
    }

    public int getFull_price() {
        return full_price;
    }
}
