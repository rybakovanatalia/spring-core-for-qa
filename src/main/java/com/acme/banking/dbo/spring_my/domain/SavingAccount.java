package com.acme.banking.dbo.spring_my.domain;

import javax.persistence.Entity;

@Entity
public class SavingAccount extends Account {
    private long id;
    private double amount;

    public SavingAccount(long id, double amount) {
        this.id = id;
        this.amount = amount;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public double getAmount() {
        return amount;
    }

    @Override
    public void withdraw(double amount) {
        if (validate(amount)) throw new IllegalStateException();
        else {
            this.amount -= amount;
        }
    }

    @Override
    public void deposit(double amount) {
        this.amount += amount;
    }

    protected boolean validate(double amount) {
        return amount > this.amount;
    }
}
