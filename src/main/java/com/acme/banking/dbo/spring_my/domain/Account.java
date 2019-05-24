package com.acme.banking.dbo.spring_my.domain;

import javax.persistence.*;

@Entity //TODO JPA Entity semantics
@Inheritance
public abstract class Account {
    abstract long getId();
    abstract double getAmount();
    abstract void withdraw(double amount);
    abstract void deposit(double amount);
}