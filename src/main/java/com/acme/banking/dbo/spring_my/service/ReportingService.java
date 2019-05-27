package com.acme.banking.dbo.spring_my.service;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportingService {
    @Autowired private AccountRepository accounts;

    //Constructor DI
    public ReportingService(AccountRepository accounts) {
        this.accounts = accounts;
    }

    //Setter DI
    public void setAccounts(AccountRepository accounts) {
        this.accounts = accounts;
    }

    public String makeAccountReport(long id) {
        Account account = accounts.findById(id);
        return "## " + account.getId() + " : " + account.getAmount();
    }
}
