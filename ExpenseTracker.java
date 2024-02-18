import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class Expense {
    private String date;
    private String category;
    private double amount;

    public Expense(String date, String category, double amount) {
        this.date = date;
        this.category = category;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }
}

public class ExpenseTracker {
    private List<User> users;
    private Map<User, List<Expense>> expenses;

    public ExpenseTracker() {
        this.users = new ArrayList<>();
        this.expenses = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        User newUser = new User(username, password);
        users.add(newUser);
        expenses.put(newUser, new ArrayList<>());
    }

    public void addExpense(User user, String date, String category, double amount) {
        Expense newExpense = new Expense(date, category, amount);
        expenses.get(user).add(newExpense);
    }

    public List<Expense> getExpensesForUser(User user) {
        return expenses.get(user);
    }

    public Map<String, Double> calculateCategorySummation(User user) {
        Map<String, Double> categorySummation = new HashMap<>();

        for (Expense expense : expenses.get(user)) {
            String category = expense.getCategory();
            double amount = expense.getAmount();

            categorySummation.put(category, categorySummation.getOrDefault(category, 0.0) + amount);
        }

        return categorySummation;
    }

    public void printUserExpenses(User user) {
        System.out.println("Expenses for user: " + user.getUsername());
        List<Expense> userExpenses = expenses.get(user);
        for (Expense expense : userExpenses) {
            System.out.println("Date: " + expense.getDate() + ", Category: " +
                    expense.getCategory() + ", Amount: " + expense.getAmount());
        }
    }

    public static void main(String[] args) {
        ExpenseTracker expenseTracker = new ExpenseTracker();

        // Register a user
        expenseTracker.registerUser("john_doe", "password123");
        System.out.println("User registered: john_doe");

        // Add expenses for the user
        User user = expenseTracker.users.get(0);
        expenseTracker.addExpense(user, "2024-02-18", "Groceries", 50.0);
        expenseTracker.addExpense(user, "2024-02-19", "Utilities", 100.0);
        System.out.println("Expenses added for user: john_doe");

        // List expenses for the user
        expenseTracker.printUserExpenses(user);

        // Calculate and display category-wise summation
        Map<String, Double> categorySummation = expenseTracker.calculateCategorySummation(user);
        System.out.println("Category-wise summation for user: john_doe");
        for (Map.Entry<String, Double> entry : categorySummation.entrySet()) {
            System.out.println("Category: " + entry.getKey() + ", Total Amount: " + entry.getValue());
        }
    }
}
