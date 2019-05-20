package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.service.TransferService;
import org.junit.Test;

import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TransferServiceTest {
    @Test
    public void shouldUpdateAccountStateWhenTransfer(){
        //region Given
        TransferService sut = new TransferService();

        Account fromAccount = mock(Account.class);
        Account toAccount =   mock(Account.class);
        //endregion

        //region When
        sut.transfer(fromAccount, toAccount, 100.);
        //endregion

        //region Then
        verify(fromAccount).withdraw(100.);
        verify(toAccount).deposit(anyDouble());
        //endregion
    }
}
