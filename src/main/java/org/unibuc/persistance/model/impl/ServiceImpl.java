package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.Service;

import java.sql.Date;

@Builder
public class ServiceImpl implements Service {

    private Long id;

    private Long accountId;

    private Date startedAt;

    private String serviceName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
