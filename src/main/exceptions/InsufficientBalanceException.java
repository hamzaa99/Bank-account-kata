package main.exceptions;

public class InsufficientBalanceException extends Exception{

    public InsufficientBalanceException() {
        super("your balance is not enough for this transaction");

    }
}
