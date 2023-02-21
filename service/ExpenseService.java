package service;

import model.ExpenseType;
import model.User;
import model.expense.AbsoluteExpense;
import model.expense.EqualExpense;
import model.expense.Expense;
import model.expense.PercentExpense;
import model.splts.Percent;
import model.splts.Split;

import java.util.List;

public class ExpenseService {
    public static Expense create(ExpenseType type, User paidBy, double amount, List<Split> splits) {
        switch(type){
            case ABSOLUTE:
                return new AbsoluteExpense(paidBy, amount, splits);
            case PERCENT:
                for (Split split : splits) {
                    Percent percentSplit = (Percent) split;
                    split.setAmount((amount * percentSplit.getPercent()) / 100.0);
                }
                return new PercentExpense(paidBy, amount, splits);
            case EQUAL:
                 int total = splits.size();
                double splitAmount = (double) (amount / total);
                for (Split split : splits) {
                    split.setAmount(splitAmount);
                }
                return new EqualExpense(paidBy, amount, splits);
        }

        return null;
    }
}
