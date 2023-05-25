package org.unibuc.persistance.model;

import java.sql.Date;

public interface BankAccount {
    Long getId();

    Long getAmmount();

    Long getAccountId();

    Date getCreatedAt();

    Long getBranchId();

    Long getEmployeeId();
}
