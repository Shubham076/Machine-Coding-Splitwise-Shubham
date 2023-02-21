package model.splts;

import model.User;

public abstract class Split {
    private User user;
    private double amount;

    Split(User user) {
        this.user = user;
    }

    Split(User user, double amount) {
        this.user = user;
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
