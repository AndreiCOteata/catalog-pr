package org.unibuc.persistance.model;

public interface Transaction {
    Long getId();

    Long getSendingAccount();

    Long getAmmount();

    String getStatus();

    Long getReceivingAccount();

    String getType();
}
