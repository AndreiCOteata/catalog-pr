package org.unibuc.persistance.repository;

import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.repository.base.BaseRepository;

public interface EmployeeRepository extends BaseRepository<Employee, Long, String> {
    boolean isEmployee(Long profileId);
}
