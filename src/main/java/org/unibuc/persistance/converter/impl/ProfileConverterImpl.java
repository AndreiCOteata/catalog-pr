package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.ProfileConverter;
import org.unibuc.persistance.dto.ProfileDto;
import org.unibuc.persistance.model.Profile;
import org.unibuc.persistance.model.impl.ProfileImpl;

public class ProfileConverterImpl implements ProfileConverter {
    @Override
    public Profile convertFromDto(ProfileDto profileDto) {
        return ProfileImpl.builder()
                .cnp(profileDto.getCnp())
                .phone(profileDto.getPhone())
                .email(profileDto.getEmail())
                .firstName(profileDto.getFirstName())
                .lastName(profileDto.getLastName())
                .addressId(profileDto.getAddressId())
                .build();
    }

    @Override
    public ProfileDto convertFromEntity(Profile profile) {
        return ProfileDto.builder()
                .cnp(profile.getCnp())
                .email(profile.getEmail())
                .firstName(profile.getFirstName())
                .lastName(profile.getLastName())
                .phone(profile.getPhone())
                .build();
    }
}
