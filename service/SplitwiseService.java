package service;
import java.util.*;

import model.ExpenseType;
import model.Pair;
import model.User;
import model.expense.Expense;
import model.splts.Split;

public class SplitwiseService {
    public List<Expense> expenses;
    public Map<String, User> users;

    // considering only for one event if we want multiple events need to create map
    public Map<String, Map<String, Double>> balances;

    public SplitwiseService() {
        expenses = new ArrayList<Expense>();
        users = new HashMap<String, User>();
        balances = new HashMap<String, Map<String, Double>>();
    }

    public void addUser(User user){
        if (!users.containsKey(user.getName())) {
            users.put(user.getName(), user);
            balances.put(user.getName(), new HashMap<String, Double>());
        }
    }

    public void addExpense(ExpenseType expenseType, double amount, String paidBy, List<Split> splits) {
        Expense expense = ExpenseService.create(expenseType, users.get(paidBy), amount, splits);
        expenses.add(expense);
        for (Split split : expense.getSplits()) {
            String paidTo = split.getUser().getName();
            Map<String, Double> balanceMap = balances.get(paidBy);
            if (!balanceMap.containsKey(paidTo)) {
                balanceMap.put(paidTo, 0.0);
            }
            balanceMap.put(paidTo, balanceMap.get(paidTo) + split.getAmount());

            balanceMap = balances.get(paidTo);
            if (!balanceMap.containsKey(paidBy)) {
                balanceMap.put(paidBy, 0.0);
            }
            balanceMap.put(paidBy, balanceMap.get(paidBy) - split.getAmount());
            System.out.println("PaidBy " + paidBy + " to : " + balances.get(paidBy.toString()));
            System.out.println("PaidTo " + paidTo + " by : " +  balances.get(paidTo.toString()));
        }
    }


    //Optional requirement 2
    public void settleWithMinTransactions() {

        //int[] 0th reprsenting username, 1st idx represents netamount.
        PriorityQueue<Pair> debit = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Pair> credit = new PriorityQueue<>(Collections.reverseOrder());

        for (Map.Entry<String, Map<String, Double>> m: balances.entrySet()) {
            String p = m.getKey();
            Map<String, Double> v = m.getValue();
            double netAmount = 0.0;
            for(double val : v.values()) {
                netAmount += val;
            }

            if (netAmount < 0.0) {
                debit.add(new Pair(p, -netAmount));
            }
            else {
                 credit.add(new Pair(p, netAmount));
            }
        }

        while (debit.size() > 0 && credit.size() > 0) {
			Pair d = debit.remove();
			Pair c = credit.remove();
            double da = d.getAmount();  //debit amount
            double ca = c.getAmount();  //credit amount
			double sment_amount = Math.min(da, ca); //settlement amount
			System.out.println(d.getName() + " should give " + sment_amount + " to " + c.getName());
			d.setAmount(da - sment_amount);
            c.setAmount(ca - sment_amount);
			if (d.getAmount() != 0) {
				debit.add(d);
			}
			if (c.getAmount() != 0) {
				credit.add(c);
			}
		}
    }
}
