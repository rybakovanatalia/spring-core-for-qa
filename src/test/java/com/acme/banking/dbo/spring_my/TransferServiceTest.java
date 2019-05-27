package com.acme.banking.dbo.spring_my;

import com.acme.banking.dbo.spring.dao.AccountRepository;
import com.acme.banking.dbo.spring.domain.Account;
import com.acme.banking.dbo.spring.service.TransferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:test-spring-context.xml", "classpath:spring-context.xml"})
@ActiveProfiles("test")
public class TransferServiceTest {

    @Autowired
    TransferService transferService;

    @MockBean
    private AccountRepository accounts;

    @Test
    public void testSuccessfulTransfer() {
        Account accFrom = mock(Account.class);
        when(accFrom.getAmount()).thenReturn(400d);
        when(accounts.findById(0L)).thenReturn(Optional.of(accFrom));

        Account accTo = mock(Account.class);
        when(accTo.getAmount()).thenReturn(100d);
        when(accounts.findById(1L)).thenReturn(Optional.of(accTo));

        transferService.transfer(0L,1L, 200.0);

        verify(accFrom, times(1)).setAmount(200);
        verify(accTo, times(1)).setAmount(300);
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionFromAccount() {
        Account accFrom = mock(Account.class);
        when(accFrom.getAmount()).thenReturn(400d);
        when(accounts.findById(1L)).thenReturn(Optional.of(accFrom));

        transferService.transfer(0L,1L, 200.0);
    }

    @Test(expected = IllegalStateException.class)
    public void testExceptionToAccount() {
        Account accFrom = mock(Account.class);
        when(accFrom.getAmount()).thenReturn(400d);
        when(accounts.findById(0L)).thenReturn(Optional.of(accFrom));

        transferService.transfer(0L,1L, 200.0);
    }
}
