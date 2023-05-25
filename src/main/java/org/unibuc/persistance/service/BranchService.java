package org.unibuc.persistance.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.converter.BranchConverter;
import org.unibuc.persistance.converter.impl.BranchConverterImpl;
import org.unibuc.persistance.dto.BranchDto;
import org.unibuc.persistance.model.Branch;
import org.unibuc.persistance.repository.BranchRepository;
import org.unibuc.persistance.repository.impl.BranchRepositoryImpl;

import java.util.zip.DataFormatException;

public class BranchService {
    private final BranchRepository repository;
    private final BranchConverter converter;

    public BranchService(){
        this.repository = new BranchRepositoryImpl();
        this.converter = new BranchConverterImpl();
    }

    public Long save(BranchDto dto) throws DataNotFoundException {
        Branch branch = converter.convertFromDto(dto);
        this.repository.add(branch);
        return this.repository.findByCode(dto.getCode()).orElseThrow(() -> new DataNotFoundException("Branch not found!"));
    }

    public Branch findById(Long id) throws DataNotFoundException {
        return this.repository.findById(id).orElseThrow(() -> new DataNotFoundException("Branch not found!"));
    }
}
