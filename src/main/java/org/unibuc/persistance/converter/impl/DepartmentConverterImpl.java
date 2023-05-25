package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.DepartmentConverter;
import org.unibuc.persistance.dto.DepartmentDto;
import org.unibuc.persistance.model.Department;
import org.unibuc.persistance.model.impl.DepartmentImpl;

public class DepartmentConverterImpl implements DepartmentConverter {
    @Override
    public Department convertFromDto(DepartmentDto departmentDto) {
        return DepartmentImpl.builder()
                .leader(departmentDto.getLeader())
                .name(departmentDto.getName())
                .id(departmentDto.getId())
                .build();
    }

    @Override
    public DepartmentDto convertFromEntity(Department department) {
        return DepartmentDto.builder()
                .id(department.getId())
                .leader(department.getLeader())
                .name(department.getName())
                .build();
    }
}
