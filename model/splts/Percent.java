package model.splts;

import model.User;

public class Percent extends Split {
    private double percent;
    public Percent(User user, double percent) {
        super(user);
        this.percent = percent;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }
}
