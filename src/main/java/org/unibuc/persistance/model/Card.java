package org.unibuc.persistance.model;

import java.sql.Date;

public interface Card {
    Long getId();

    Long getNumber();

    Date getExpiryDate();

    Integer getCvv();

    Long getAccountId();

    String getStatus();
}
