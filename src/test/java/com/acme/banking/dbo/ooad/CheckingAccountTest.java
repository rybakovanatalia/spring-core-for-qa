package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.CheckingAccount;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CheckingAccountTest {

    @Test(expected = IllegalStateException.class)
    public void shouldGetExceptionWhenWithdrawInvalidAmount() {
        //Given
        CheckingAccount testCheckingAccount = new CheckingAccount(0,100,300);
        //When
        testCheckingAccount.withdraw(500);
    }

    @Test()
    public void shouldGetCorrectAmountAfterWithdrawValidAmount() {
        //Given
        CheckingAccount testCheckingAccount = new CheckingAccount(1,100,300);
        //When
        testCheckingAccount.withdraw(300);
        //Then
        double amount = testCheckingAccount.getAmount();
        assertTrue("Amount was " + amount, amount == -200.);
    }
}
