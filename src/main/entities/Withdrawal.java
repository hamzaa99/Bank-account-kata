package main.entities;

import main.exceptions.InsufficientBalanceException;
import main.exceptions.NegativeTransactionAmountException;

public class Withdrawal extends Transaction {
    public Withdrawal(Long id, Double amount, BankAccount account) throws NegativeTransactionAmountException {
        super(id, amount, account);
    }

    @Override
    public void execute( ) throws InsufficientBalanceException {
        if(this.getAccount().getBalance() >= this.getAmount() )
            this.getAccount().setBalance(this.getAccount().getBalance() - this.getAmount());
        else throw new InsufficientBalanceException();
    }

    @Override
    public String getTransactionType() {
        return "withdrawal";
    }
}
