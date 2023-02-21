package model.expense;
import java.util.*;

import model.User;
import model.splts.Split;

public abstract class Expense {
    private User paidBy;
    private double amount;
    private List<Split> splits;

    Expense(User paidBy, double amount, List<Split> splits) {
        this.paidBy = paidBy;
        this.amount = amount;
        this.splits = splits;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }
}
