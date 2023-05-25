package org.unibuc.persistance.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.converter.ProfileConverter;
import org.unibuc.persistance.converter.impl.ProfileConverterImpl;
import org.unibuc.persistance.dto.ProfileDto;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.repository.ProfileRepository;
import org.unibuc.persistance.repository.impl.ProfileRepositoryImpl;

import java.util.zip.DataFormatException;

public class ProfileService {

    private final ProfileRepository repository;
    private final ProfileConverter converter;

    public ProfileService() {
        this.converter = new ProfileConverterImpl();
        this.repository = new ProfileRepositoryImpl();
    }

    public Profile save(ProfileDto dto) throws DataNotFoundException {
        Profile profile = converter.convertFromDto(dto);
        repository.add(profile);
        return repository.findBy(profile.getEmail()).orElseThrow(() -> new DataNotFoundException("Profile not found!"));
    }

    public Profile find(Long id) throws DataNotFoundException {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException("Profile not found!"));
    }


}
