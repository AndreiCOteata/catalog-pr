package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.Transaction;

@Builder
public class TransactionImpl implements Transaction {

    private Long id;

    private Long sendingAccount;

    private Long ammount;

    private String status;

    private Long receivingAccount;

    private String type;

    public Long getId() {
        return id;
    }

    public Long getSendingAccount() {
        return sendingAccount;
    }

    public Long getAmmount() {
        return ammount;
    }

    public String getStatus() {
        return status;
    }

    public Long getReceivingAccount() {
        return receivingAccount;
    }

    public String getType() {
        return type;
    }
}
