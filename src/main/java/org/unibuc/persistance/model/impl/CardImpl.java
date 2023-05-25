package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.Card;

import java.sql.Date;

@Builder
public class CardImpl implements Card {

    private Long id;

    private Long number;

    private Date expiryDate;

    private Integer cvv;

    private Long accountId;

    private String status;

    public Long getId() {
        return id;
    }

    public Long getNumber() {
        return number;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Integer getCvv() {
        return cvv;
    }

    public Long getAccountId() {
        return accountId;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setCvv(Integer cvv) {
        this.cvv = cvv;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
