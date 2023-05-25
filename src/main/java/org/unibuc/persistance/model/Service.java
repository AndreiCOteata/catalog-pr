package org.unibuc.persistance.model;

import java.sql.Date;

public interface Service {
    Long getId();

    Long getAccountId();

    Date getStartedAt();

    String getServiceName();
}
