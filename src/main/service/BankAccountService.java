package main.service;

import main.entities.*;
import main.exceptions.InsufficientBalanceException;
import main.exceptions.NegativeTransactionAmountException;

import java.util.ArrayList;

public class BankAccountService {

    //  make a deposit into an account
    public void deposit(BankClient client, Double amount) throws NegativeTransactionAmountException, InsufficientBalanceException {
        Transaction transaction = new Deposit((long) (client.getBankAccount().getTransactionsHistory().size() + 1),amount,client.getBankAccount());
        client.getBankAccount().addTransaction(transaction);
    }
    // withdraw from an account
    public void withdraw(BankClient client, Double amount) throws NegativeTransactionAmountException, InsufficientBalanceException {
        Transaction transaction = new Withdrawal((long) (client.getBankAccount().getTransactionsHistory().size() + 1),amount,client.getBankAccount());
        client.getBankAccount().addTransaction(transaction);
    }
    // get transaction history
    public ArrayList<Transaction> transactionHistory(BankClient client) {
        return client.getBankAccount().getTransactionsHistory();
    }
}
