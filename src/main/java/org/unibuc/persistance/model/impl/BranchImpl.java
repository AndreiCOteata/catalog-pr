package org.unibuc.persistance.model.impl;

import lombok.Builder;
import org.unibuc.persistance.model.Branch;

@Builder
public class BranchImpl implements Branch {
    private Long id;

    private Long addressId;

    private Long code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }
}
