package ru.yandex.yandexlavka.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import ru.yandex.yandexlavka.entity.CourierType;
import ru.yandex.yandexlavka.model.ECourierType;
import ru.yandex.yandexlavka.repository.CourierTypeRepository;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public abstract class CourierTypeMapper {
    @Autowired
    private CourierTypeRepository courierTypeRepository;

    CourierType toEntity(ECourierType type) {

        return type == null ? null : courierTypeRepository.readBy(type).orElseThrow();
    }

    ECourierType toDto(CourierType type) {
        return type == null ? null : type.getType();
    }
}
