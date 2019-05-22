package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.SavingAccount;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;

import static org.fest.assertions.api.Assertions.assertThat;

public class SavingAccountTest {

    @Test(expected = IllegalStateException.class)
    public void shouldGetExceptionWhenWithdrawInvalidAmount() {
        //Given
        SavingAccount testSavingAccount = new SavingAccount(0,100);
        //When
        testSavingAccount.withdraw(200);
    }

    @Test()
    public void shouldGetCorrectAmountAfterWithdrawValidAmount() {
        //Given
        SavingAccount testSavingAccount = new SavingAccount(0,100);
        //When
        testSavingAccount.withdraw(100);
        //Then
        double amount = testSavingAccount.getAmount();
        assertThat(amount).isEqualTo(0.);
    }

    @Test()
    public void shouldGetCorrectAmountWhenWithdrawValidAmount() {
        //Given
        SavingAccount testSavingAccount = new SavingAccount(0,100);
        //When
        testSavingAccount.withdraw(100);
        //Then
        double amount = testSavingAccount.getAmount();
        assertThat(amount).isEqualTo(0);
    }

    @Test()
    public void shouldGetCorrectAmountWhenDepositAmount() {
        //Given
        SavingAccount testSavingAccount = new SavingAccount(0,100);
        //When
        testSavingAccount.deposit(100);
        //Then
        double amount = testSavingAccount.getAmount();
        assertThat(amount).isEqualTo(200);
    }

    @Test()
    public void shouldGetIdWhenSavingAccountIsCreated() {
        //Given
        SavingAccount testSavingAccount = new SavingAccount(0,100);
        //When
        long id = testSavingAccount.getId();
        //Then
        assertThat(id).isEqualTo(0);
    }

}
