package main.entities;

import main.exceptions.NegativeTransactionAmountException;

public class Deposit extends Transaction{
    public Deposit(Long id, Double amount, BankAccount account) throws NegativeTransactionAmountException {
        super(id, amount, account);
    }

    @Override
    public void execute() {
        this.getAccount().setBalance(this.getAccount().getBalance() + this.getAmount());
    }

    @Override
    public String getTransactionType() {
        return "Deposit";
    }
}
