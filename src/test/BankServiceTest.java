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

import static org.junit.jupiter.api.Assertions.*;


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
        assertAll(
                () -> assertEquals("Deposit",this.testClient.getBankAccount().getTransactionsHistory().get(0).getTransactionType()),
                () -> assertEquals("Withdrawal",this.testClient.getBankAccount().getTransactionsHistory().get(1).getTransactionType()),
                () -> assertEquals(1000.,this.testClient.getBankAccount().getTransactionsHistory().get(0).getAmount()),
                () -> assertEquals(500.,this.testClient.getBankAccount().getTransactionsHistory().get(1).getAmount())
        );

    }
}
