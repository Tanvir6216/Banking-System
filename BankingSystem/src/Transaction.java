public class Transaction {

    // Default constructor
    public Transaction() {
    }

    // Transfer method: transfers an amount from one account to another
    public void transfer(Account fromAccount, Account toAccount, double amount) {
        if (amount > 0 && fromAccount.getBalance() >= amount) {
            fromAccount.withdraw(amount); // Withdraw from sender
            toAccount.deposit(amount);    // Deposit to receiver
        } else {
            System.out.println("Transfer failed: Insufficient funds or invalid amount.");
        }
    }
}