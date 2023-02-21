import model.ExpenseType;
import model.User;
import model.splts.Absolute;
import model.splts.Equal;
import model.splts.Percent;
import model.splts.Split;
import service.SplitwiseService;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        SplitwiseService ss = new SplitwiseService();
        ss.addUser(new User("A", "a@gmail.com"));
        ss.addUser(new User("B", "b@gmail.com"));
        ss.addUser(new User("C", "c@gmail.com"));
        ss.addUser(new User("D", "d@gmail.com"));

        Scanner scn = new Scanner(System.in);
        int count = 0;
        while (true) {
            String[] inp = scn.nextLine().split(" ");
            String[] friends  = scn.nextLine().split(" ");
            count++;
            String expenseType = inp[0];
            String paidBy = inp[1];
            double amount = Double.parseDouble(inp[2]);
            List<Split> splits = new ArrayList<>();

            switch (expenseType) {
                case "EQUAL":
                    for (int i = 0; i < friends.length; i++) {
                        splits.add(new Equal(ss.users.get(friends[i]), amount));
                    }
                    ss.addExpense(ExpenseType.EQUAL, amount, paidBy, splits);
                    break;
                case "ABSOLUTE":
                    for (int i = 0; i < friends.length; i++) {
                        splits.add(new Absolute(ss.users.get(friends[i]), amount));
                    }
                    ss.addExpense(ExpenseType.ABSOLUTE, amount, paidBy, splits);
                    break;
                case "PERCENT":
                    for (int i = 0; i < friends.length; i++) {
                        System.out.println("Enter the percentage for friend: " + friends[i]);
                        double percent = Double.parseDouble(scn.nextLine());
                        splits.add(new Percent(ss.users.get(friends[i]), percent));
                    }
                    ss.addExpense(ExpenseType.PERCENT, amount, paidBy, splits);
                    break;
            }

            if (count == 2) {
                ss.settleWithMinTransactions();
                break;
            }
        }
    }
}
