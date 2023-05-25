package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.EmployeeConverter;
import org.unibuc.persistance.dto.EmployeeDto;
import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.model.impl.EmployeeImpl;

public class EmployeeConverterImpl implements EmployeeConverter {
    @Override
    public Employee convertFromDto(EmployeeDto employeeDto) {
        return EmployeeImpl.builder()
                .id(employeeDto.getId())
                .departmentId(employeeDto.getDepartmentId())
                .startedAt(employeeDto.getStartedAt())
                .salary(employeeDto.getSalary())
                .profileId(employeeDto.getProfileId())
                .position(employeeDto.getPosition())
                .branchId(employeeDto.getBranchId())
                .build();
    }

    @Override
    public EmployeeDto convertFromEntity(Employee employee) {
        return EmployeeDto.builder()
                .id(employee.getId())
                .branchId(employee.getBranchId())
                .departmentId(employee.getDepartmentId())
                .salary(employee.getSalary())
                .position(employee.getPosition())
                .profileId(employee.getProfileId())
                .startedAt(employee.getStartedAt())
                .build();
    }
}
