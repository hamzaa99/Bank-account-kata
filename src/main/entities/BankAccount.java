package main.entities;

import main.exceptions.InsufficientBalanceException;

import java.util.ArrayList;

public class BankAccount {

    private Long id;
    private BankClient owner;
    private Double balance;
    ArrayList<Transaction> transactionsHistory;

    public BankAccount(Long id, BankClient owner, Double balance) {
        this.id = id;
        this.owner = owner;
        this.balance = balance;
        this.transactionsHistory = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BankClient getOwner() {
        return owner;
    }

    public void setOwner(BankClient owner) {
        this.owner = owner;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactionsHistory() {
        return transactionsHistory;
    }

    public void addTransaction(Transaction transaction) throws InsufficientBalanceException {
        transaction.execute();
        this.transactionsHistory.add(transaction);
    }

}
