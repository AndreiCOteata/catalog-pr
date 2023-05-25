package org.unibuc.persistance.model;

import java.sql.Date;

public interface Employee {
    Long getId();

    Long getProfileId();

    String getPosition();

    Long getDepartmentId();

    Date getStartedAt();

    Long getSalary();

    Long getBranchId();
}
