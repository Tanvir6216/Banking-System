import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

@SuppressWarnings("serial")
public class GUI extends JFrame {
    private JComboBox<String> accountSelector;
    private JTextField amountField;
    private JButton depositButton;
    private JButton withdrawButton;
    private JButton transferButton;
    private JButton showBalanceButton;
    private JLabel transactionStatusLabel;
    private Map<String, Account> accountsMap;

    @SuppressWarnings("unused")
	public GUI(LinkedList<Account> accounts) {
        super("Banking System");
        setLayout(new FlowLayout());

        accountsMap = new HashMap<>();
        for (Account acc : accounts) {
            accountsMap.put(acc.getFullName(), acc);
        }

       
        accountSelector = new JComboBox<>(accountsMap.keySet().toArray(new String[0]));
        amountField = new JTextField(10);
        depositButton = new JButton("Deposit");
        withdrawButton = new JButton("Withdraw");
        transferButton = new JButton("Transfer");
        showBalanceButton = new JButton("Account Balance");
        transactionStatusLabel = new JLabel("Transaction Status");

        add(new JLabel("Select Account:"));
        add(accountSelector);
        add(new JLabel("Amount:"));
        add(amountField);
        add(depositButton);
        add(withdrawButton);
        add(transferButton);
        add(showBalanceButton);
        add(transactionStatusLabel);

       
        depositButton.addActionListener(e -> performDeposit());
        withdrawButton.addActionListener(e -> performWithdraw());
        transferButton.addActionListener(e -> performTransfer());
        showBalanceButton.addActionListener(e -> displayAccountDetails((String) accountSelector.getSelectedItem()));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setVisible(true);
    }

    private void performDeposit() {
        String accountName = (String) accountSelector.getSelectedItem();
        Account account = accountsMap.get(accountName);
        if (account != null) {
            double amount = Double.parseDouble(amountField.getText());
            account.deposit(amount);
            displayAccountDetails(accountName);
            transactionStatusLabel.setText("$" + amount + " deposited into " + account.getFullName());
        }
    }

    private void performWithdraw() {
        String accountName = (String) accountSelector.getSelectedItem();
        Account account = accountsMap.get(accountName);
        if (account != null) {
            double amount = Double.parseDouble(amountField.getText());
            if (account.getBalance() >= amount) {
                account.withdraw(amount);
                displayAccountDetails(accountName);
                transactionStatusLabel.setText("$" + amount + " withdrawn from " + account.getFullName());
            } else {
                transactionStatusLabel.setText("Insufficient funds for withdrawal from " + account.getFullName());
            }
        }
    }

    private void performTransfer() {
        String senderName = (String) accountSelector.getSelectedItem();
        String receiverName = JOptionPane.showInputDialog("Enter Receiver's Name:");
        if (receiverName != null) {
            if (accountsMap.containsKey(receiverName)) {
                Account senderAccount = accountsMap.get(senderName);
                Account receiverAccount = accountsMap.get(receiverName);
                double amount = Double.parseDouble(amountField.getText());
                if (senderAccount.getBalance() >= amount) {
                    senderAccount.withdraw(amount);
                    receiverAccount.deposit(amount);
                    displayAccountDetails(senderName);
                    displayAccountDetails(receiverName);
                    transactionStatusLabel.setText("Transfer of $" + amount + " from " + senderAccount.getFullName() + " to " + receiverAccount.getFullName() + " successful.");
                } else {
                    transactionStatusLabel.setText("Insufficient funds for transfer from " + senderAccount.getFullName());
                }
            } else {
                transactionStatusLabel.setText("Receiver account not found.");
            }
        }
    }

    private void displayAccountDetails(String accountName) {
        Account account = accountsMap.get(accountName);
        if (account != null) {
            JOptionPane.showMessageDialog(this, "Account Holder: " + account.getFullName() + "\nAccount Number: " + account.getAccountNumber() + "\nBalance: $" + account.getBalance(), "Account Details", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    static class Account {
        private String firstName;
        private String lastName;
        private int accountNumber;
        private double balance;

        public Account(String firstName, String lastName, int accountNumber, double balance) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.accountNumber = accountNumber;
            this.balance = balance;
        }

        public String getFullName() {
            return firstName + " " + lastName;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            balance += amount;
        }

        public void withdraw(double amount) {
            balance -= amount;
        }
    }

    public static void main(String[] args) {
    	LinkedList<Account> accounts = new LinkedList<>();
        accounts.add(new Account("Hiran", "Patel", 11, 10000));
        accounts.add(new Account("Pooja", "Kaur", 22, 10));
        accounts.add(new Account("David", "Phillips", 33, 50000));
        accounts.add(new Account("Praveen", "Chandramenon", 44, 99999));
        accounts.add(new Account("Salman", "Arif", 55, 65678));

        new GUI(accounts);
    }
}
