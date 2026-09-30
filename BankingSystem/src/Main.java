import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
                ReadAccounts readAccounts = new ReadAccounts("C:\\Users\\DELL\\eclipse-workspace\\BankingSystem\\src");
        
        LinkedList<String> firstNames = readAccounts.getFirstNames();
        LinkedList<String> lastNames = readAccounts.getLastNames();
        LinkedList<Integer> accountNumbers = readAccounts.getAccountNumbers();
        LinkedList<Integer> balances = readAccounts.getBalances();
        
                LinkedList<Account> accounts = new LinkedList<>();
        for (int i = 0; i < firstNames.size(); i++) {
            accounts.add(new Account(firstNames.get(i), lastNames.get(i), accountNumbers.get(i), balances.get(i)));
        }
        
        
        for (Account account : accounts) {
            System.out.println("Account: " + account.getAccountNumber() + ", Balance: " + account.getBalance());
           
        }
    }
}
