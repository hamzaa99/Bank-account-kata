package main.entities;

import main.exceptions.InsufficientBalanceException;
import main.exceptions.NegativeTransactionAmountException;

import java.time.Instant;
import java.util.Date;

public abstract class Transaction {
    private Long id;
    private Double amount;
    private Date date;
    private BankAccount account;

    public Transaction(Long id, Double amount, BankAccount account) throws NegativeTransactionAmountException {
        if (amount > 0) {
            this.id = id;
            this.amount = amount;
            this.date = Date.from(Instant.now());
            this.account = account;
        }
        else throw new NegativeTransactionAmountException();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public BankAccount getAccount() {
        return account;
    }

    public void setAccount(BankAccount account) {
        this.account = account;
    }

    public abstract void execute() throws InsufficientBalanceException;
    public abstract String getTransactionType();


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", date=" + date +
                ", account=" + account +
                '}';
    }
}
