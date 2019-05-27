package com.acme.banking.dbo.spring_my;

import com.acme.banking.dbo.spring.dao.AccountRepository;
import com.acme.banking.dbo.spring.domain.Account;
import com.acme.banking.dbo.spring.service.ReportingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration({"classpath:test-spring-context.xml", "classpath:spring-context.xml"})
@ActiveProfiles("test")

public class ReportingServiceTest {
    @MockBean
    private AccountRepository accounts;

    @Autowired
    ReportingService reportingService;

    @Autowired
    Logger logger;

    @Test
    public void testAccountReport() {
        Account stubAccount = mock(Account.class);
        when(stubAccount.toString()).thenReturn("0 200.0");
        when(accounts.findById(anyLong())).thenReturn(Optional.of(stubAccount));
        String reportString = reportingService.accountReport(0);
        assertEquals(reportString, "## 0 200.0");
        logger.info("Reporting String : {}", reportString);
    }

    @Test
    public void testChangeOfLayoutMarker() {
        Account stubAccount = mock(Account.class);
        when(stubAccount.toString()).thenReturn("0 200.0");
        when(accounts.findById(anyLong())).thenReturn(Optional.of(stubAccount));
        reportingService.setLayoutMarker("-");
        String reportString = reportingService.accountReport(0);
        assertEquals(reportString, "-- 0 200.0");
        logger.info("Reporting String : {}", reportString);
    }

    @Test(expected = IllegalStateException.class)
    public void testErrorAccountReport() {
        reportingService.accountReport(1);
    }
}
