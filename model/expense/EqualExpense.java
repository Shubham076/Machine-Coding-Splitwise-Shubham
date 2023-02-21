package model.expense;
import java.util.*;
import model.User;
import model.splts.Split;

public class EqualExpense extends Expense  {
    public EqualExpense(User paidBy, double amount, List<Split> splits){
        super(paidBy, amount, splits);
    }
}
