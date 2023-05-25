package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.Department;

@Builder
public class DepartmentImpl implements Department {

    private Long id;

    private String name;

    private Long leader;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getLeader() {
        return leader;
    }

    public void setLeader(Long leader) {
        this.leader = leader;
    }
}
