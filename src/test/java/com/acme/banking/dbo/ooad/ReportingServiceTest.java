package com.acme.banking.dbo.ooad;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import com.acme.banking.dbo.ooad.service.ReportingService;
import org.junit.Test;

import javax.persistence.EntityNotFoundException;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReportingServiceTest {

    @Test(expected = EntityNotFoundException.class)
    public void shouldGetExceptionWhenNoAccountFound() {
        AccountRepository stubAccountRepository = mock(AccountRepository.class);
        when(stubAccountRepository.findById(anyLong()))
                .thenThrow(new EntityNotFoundException());
        ReportingService sut = new ReportingService(stubAccountRepository);
        sut.reportForAccount(0L);

    }

    @Test
    public void shouldGetReportForAccountWhenAccountExists(){
        //region Given
        Account stubAccount = mock(Account.class);
        when(stubAccount.getAmount()).thenReturn(100.0);
        when(stubAccount.getId()).thenReturn(0L);
        //endregion

        AccountRepository stubRepo = mock(AccountRepository.class);
        when(stubRepo.findById(0L)).thenReturn(stubAccount);

        ReportingService sut = new ReportingService(stubRepo);

        //region When
        String actualReport = sut.reportForAccount(0L);
        //endregion

        //region Then
        assertThat(actualReport)
                .contains("##")
                .contains(String.valueOf(0L))
                .contains("100.0");
        //endregion
    }

    @Test
    public void shouldReportNewAccountDataWhenSet(){

        //region Given
        Account stubAccount1 = mock(Account.class);
        when(stubAccount1.getAmount()).thenReturn(100.0);
        when(stubAccount1.getId()).thenReturn(0L);

        Account stubAccount2 = mock(Account.class);
        when(stubAccount2.getAmount()).thenReturn(300.0);
        when(stubAccount2.getId()).thenReturn(1L);
        //endregion

        AccountRepository stubRepo1 = mock(AccountRepository.class);
        when(stubRepo1.findById(0L)).thenReturn(stubAccount1);

        AccountRepository stubRepo2 = mock(AccountRepository.class);
        when(stubRepo2.findById(1L)).thenReturn(stubAccount2);

        ReportingService sut = new ReportingService(stubRepo1);

        //region When
        sut.setAccounts(stubRepo2);
        String actualReport = sut.reportForAccount(1L);
        //endregion

        //region Then
        assertThat(actualReport)
                .contains("##")
                .contains(String.valueOf(1L))
                .contains("300.0");
        //endregion
    }

}
