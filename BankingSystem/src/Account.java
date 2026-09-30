
public class Account extends Customer {
	private double balance;
	 private int accountNumber;
	
	  public Account(String firstName, String lastName, double balance, int accountNumber) {
         
		  super(firstName, lastName);
        
		  this.balance = balance;
        
		  this.accountNumber = accountNumber;
    }

            public double getBalance() {
                   return balance;
      }

            public int getAccountNumber() {
                    return accountNumber;
      } 

             public void deposit(double amount) {
                      balance += amount;
     }

               public void withdraw(double amount) {
                       if (amount <= balance) {
                        balance -= amount;
            } else {
        

            	System.out.println("Insufficient funds");
    
            
            }
    }
}

