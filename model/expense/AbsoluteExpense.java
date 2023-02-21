package model.expense;
import model.User;
import model.splts.Split;

import java.util.*;

public class AbsoluteExpense extends Expense {
    public AbsoluteExpense(User paidBy, double amount, List<Split> splits){
        super(paidBy, amount, splits);
    }
}
