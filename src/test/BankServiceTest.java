package test;

import main.entities.BankClient;
import main.entities.Deposit;
import main.entities.Transaction;
import main.entities.Withdrawal;
import main.exceptions.InsufficientBalanceException;
import main.exceptions.NegativeTransactionAmountException;
import main.service.BankAccountService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class BankServiceTest {

    private final BankClient testClient = new BankClient(1L,"nassim","medjnoun");
    private final BankAccountService bankAccountService = new BankAccountService();

    @Test
    public void accountStartAtZeroTest(){
        assertEquals(0,testClient.getBankAccount().getBalance());
    }

    @Test
    public void simpleDepositTest() throws NegativeTransactionAmountException, InsufficientBalanceException {
        bankAccountService.deposit(testClient,500.);
        assertEquals(500.,testClient.getBankAccount().getBalance());
    }
    @Test
    public void simpleWithdrawalTest() throws NegativeTransactionAmountException, InsufficientBalanceException {
        //initial deposit
        bankAccountService.deposit(testClient,1000.);
        bankAccountService.withdraw(testClient,500.);
        assertEquals(500.,testClient.getBankAccount().getBalance());

    }
    @Test
    public void negativeDepositTest(){
        Exception e = assertThrows(NegativeTransactionAmountException.class,() -> bankAccountService.deposit(testClient,-100.));
        assertEquals("transaction can not have a negative amount",e.getMessage());

    }
    @Test
    public void negativeWithdrawalTest(){
        Exception e = assertThrows(NegativeTransactionAmountException.class,() -> bankAccountService.withdraw(testClient,-100.));
        assertEquals("transaction can not have a negative amount",e.getMessage());

    }
    @Test
    public void insufficientBalanceWithdrawalTest(){
        Exception e = assertThrows(InsufficientBalanceException.class,() -> bankAccountService.withdraw(testClient,100.));
        assertEquals("your balance is not enough for this transaction",e.getMessage());

    }

    @Test
    public void transactionHistoryTest() throws NegativeTransactionAmountException, InsufficientBalanceException {
        bankAccountService.deposit(testClient,1000.);
        bankAccountService.withdraw(testClient,500.);

        Transaction simpleDeposit = new Deposit(1L, 1000.,testClient.getBankAccount());
        Transaction simpleWithdrawal = new Withdrawal(2L, 500.,testClient.getBankAccount());
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(simpleDeposit);
        transactions.add(simpleWithdrawal);
        assertEquals(transactions.toString(),testClient.getBankAccount().getTransactionsHistory().toString());
    }
}
