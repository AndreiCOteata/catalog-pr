package org.unibuc.persistance.converter.base;

import java.util.List;
import java.util.stream.Collectors;

public interface BaseConverter<T, V>{
    T convertFromDto(V v);
    V convertFromEntity(T t);

    default List<T> listFromDtos(List<V> dtos){
        return dtos.stream().map(this::convertFromDto).collect(Collectors.toList());
    }

    default List<V> listFromEntities(List<T> dtos){
        return dtos.stream().map(this::convertFromEntity).collect(Collectors.toList());
    }
}
