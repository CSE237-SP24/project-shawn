class BankAccount {
    private String name;
    private int id;
    private int birthday;
    private int balance;

    public BankAccount(String name, int id, int birthday) {
        this.name = name;
        this.id = id;
        this.birthday = birthday;
    }

    public void deposit(int money) {
        if(money <= 0) {
            System.out.println("Invalid deposit amount. Please enter a number above 0");
        }else {
            balance += money;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int money) {
        if (money <= 0) {
            System.out.println("Invalid withdraw amount. Please enter an number greater than 0.");
        }else if(balance <= money) {
            System.out.println("You don't have enough money to withdraw this. You need "+(money-balance)+" more dollars.");
        }else {
            balance -= money;
            System.out.println("You have successfully withdrew "+money+" dollars. You still have "+balance+" dollars left in your account.");
        }
    }

}