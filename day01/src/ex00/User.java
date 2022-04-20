package ex00;

public class User {
    private String  name;
    private Integer id;
    private Integer balance;

    User(String name, int id, int balance) {
        this.balance = balance;
		this.name = name;
        this.id = id;
        checkBalance();
    }

	private void	checkBalance() {
		if (balance < 0) {
			System.err.println("User " + name + " has negative balance"
                    + " (id: " + id + ')');
            System.err.println("Defaulting balance to zero...");
		    balance = 0;
        }
	}

    public int  getBalance() {
        return balance;
    }

    public int  getId() {
        return id;
    }

    public String   getName() {
        return name;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
