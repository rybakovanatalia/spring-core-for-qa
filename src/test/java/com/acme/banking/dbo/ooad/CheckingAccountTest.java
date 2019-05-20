package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.CheckingAccount;
import org.junit.Test;

import static org.fest.assertions.api.Assertions.assertThat;

public class CheckingAccountTest {

    @Test(expected = IllegalStateException.class)
    public void shouldGetExceptionWhenWithdrawInvalidAmount() {
        //Given
        CheckingAccount testSavingAccount = new CheckingAccount(0,100,300);
        //When
        testSavingAccount.withdraw(500);
    }

    @Test()
    public void shouldGetCorrectAmountAfterWithdrawValidAmount() {
        //Given
        CheckingAccount testSavingAccount = new CheckingAccount(0,100,300);
        //When
        testSavingAccount.withdraw(200);
        //Then
        double amount = testSavingAccount.getAmount();
        assertThat(amount).isEqualTo(-100.);
    }
}
