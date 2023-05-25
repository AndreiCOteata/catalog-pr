package org.unibuc.persistance.converter.impl;

import org.unibuc.persistance.converter.ServiceConverter;
import org.unibuc.persistance.dto.ServiceDto;
import org.unibuc.persistance.model.Service;
import org.unibuc.persistance.model.impl.ServiceImpl;

public class ServiceConverterImpl implements ServiceConverter {
    @Override
    public Service convertFromDto(ServiceDto serviceDto) {
        return ServiceImpl.builder()
                .serviceName(serviceDto.getServiceName())
                .id(serviceDto.getId())
                .accountId(serviceDto.getAccountId())
                .startedAt(serviceDto.getStartedAt())
                .build();
    }

    @Override
    public ServiceDto convertFromEntity(Service service) {
        return ServiceDto.builder()
                .accountId(service.getAccountId())
                .id(service.getId())
                .serviceName(service.getServiceName())
                .startedAt(service.getStartedAt())
                .build();
    }
}
