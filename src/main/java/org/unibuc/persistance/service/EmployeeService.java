package org.unibuc.persistance.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.converter.EmployeeConverter;
import org.unibuc.persistance.converter.impl.EmployeeConverterImpl;
import org.unibuc.persistance.model.Employee;
import org.unibuc.persistance.repository.EmployeeRepository;
import org.unibuc.persistance.repository.impl.EmployeeRepositoryImpl;

public class EmployeeService {
    private final EmployeeConverter converter;
    private final EmployeeRepository repository;

    public EmployeeService(){
        this.converter = new EmployeeConverterImpl();
        this.repository = new EmployeeRepositoryImpl();
    }

    public boolean isEmployee(Long profileId){
        return repository.isEmployee(profileId);
    }

    public Employee find(Long profileId) throws DataNotFoundException {
        return repository.findBy(String.valueOf(profileId)).orElseThrow(() -> new DataNotFoundException("Employee not found!"));
    }
}
