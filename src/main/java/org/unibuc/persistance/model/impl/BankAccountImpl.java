package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.BankAccount;

import java.sql.Date;

@Builder
public class BankAccountImpl implements BankAccount {
    private Long id;

    private Long ammount;

    private Long accountId;

    private Date createdAt;

    private Long branchId;

    private Long employeeId;

    public Long getId() {
        return id;
    }

    public Long getAmmount() {
        return ammount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getBranchId() {
        return branchId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmmount(Long ammount) {
        this.ammount = ammount;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setBranchId(Long branchId) {
        this.branchId = branchId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
}
