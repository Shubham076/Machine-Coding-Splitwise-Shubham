package model.expense;
import java.util.*;
import model.User;
import model.splts.Split;

public class PercentExpense extends Expense{
    public PercentExpense(User paidBy, double amount, List<Split> splits){
        super(paidBy, amount, splits);
    }
}
