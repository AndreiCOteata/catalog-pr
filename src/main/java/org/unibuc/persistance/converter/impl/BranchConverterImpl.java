package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.BranchConverter;
import org.unibuc.persistance.dto.BranchDto;
import org.unibuc.persistance.model.Branch;
import org.unibuc.persistance.model.impl.BranchImpl;

public class BranchConverterImpl implements BranchConverter {
    @Override
    public Branch convertFromDto(BranchDto branchDto) {
        return BranchImpl.builder()
                .code(branchDto.getCode())
                .id(branchDto.getId())
                .addressId(branchDto.getAddressId())
                .build();
    }

    @Override
    public BranchDto convertFromEntity(Branch branch) {
        return BranchDto.builder()
                .addressId(branch.getAddressId())
                .id(branch.getId())
                .code(branch.getCode())
                .build();
    }
}
