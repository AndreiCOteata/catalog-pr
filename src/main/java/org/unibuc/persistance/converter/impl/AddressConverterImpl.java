package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.AddressConverter;
import org.unibuc.persistance.dto.AddressDto;
import org.unibuc.persistance.model.Address;
import org.unibuc.persistance.model.impl.AddressImpl;

public class AddressConverterImpl implements AddressConverter {
    @Override
    public Address convertFromDto(AddressDto addressDto) {
        return AddressImpl.builder()
                .city(addressDto.getCity())
                .country(addressDto.getCountry())
                .street(addressDto.getStreet())
                .number(addressDto.getNumber())
                .build();
    }

    @Override
    public AddressDto convertFromEntity(Address address) {
        return AddressDto.builder()
                .city(address.getCity())
                .country(address.getCountry())
                .street(address.getStreet())
                .number(address.getNumber())
                .build();
    }
}
