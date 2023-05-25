package org.unibuc.persistance.repository;

import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.model.Address;
import org.unibuc.persistance.repository.base.BaseRepository;

import java.util.Optional;

public interface AddressRepository extends BaseRepository<Address, Long, String> {
    Optional<Address> findAddress(AddressDto addressDto);
}
