package com.acme.banking.dbo.spring_my.dao;

import com.acme.banking.dbo.spring_my.domain.Account;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** In use will be code-generated implementation by Spring-data-jpa module */
@Repository
@Profile({"prod", "system-test"})
public interface AccountRepository extends JpaRepository<Account, Long> {
    /** TODO Intro to Optional class */
}
