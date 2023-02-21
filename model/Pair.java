package model;

public class Pair implements Comparable<Pair>{
    private String name;
    private double amount;

    public Pair(String name, double amount) {
        this.name = name;
        this.amount = amount;
    }

    public int compareTo(Pair o) {
        return (int)(this.amount - o.amount);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
