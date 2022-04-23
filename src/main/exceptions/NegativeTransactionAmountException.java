package main.exceptions;

public class NegativeTransactionAmountException extends Exception{
    public NegativeTransactionAmountException() {
        super("transaction can not have a negative amount");
    }
}
