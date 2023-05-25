package org.unibuc.persistance.service;

import org.unibuc.exception.DataNotFoundException;
import org.unibuc.persistance.converter.AddressConverter;
import org.unibuc.persistance.converter.impl.AddressConverterImpl;
import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.model.Address;
import org.unibuc.persistance.repository.AddressRepository;
import org.unibuc.persistance.repository.impl.AddresRepositoryImpl;

import java.util.zip.DataFormatException;

public class AddressService {
    private final AddressRepository repository = new AddresRepositoryImpl();
    private final AddressConverter converter = new AddressConverterImpl();
    public Address save(AddressDto dto) throws DataNotFoundException {
        Address address = converter.convertFromDto(dto);
        repository.add(address);
        return repository.findAddress(dto).orElseThrow(() -> new DataNotFoundException("Address not found!"));
    }
}
