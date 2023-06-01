package ru.yandex.yandexlavka.mapper;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.dto.CreateCourierDto;
import ru.yandex.yandexlavka.entity.Courier;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RegionMapper.class, TimeIntervalMapper.class, CourierTypeMapper.class},
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface CourierMapper {
    @Mapping(target = "courierId", source = "id")
    @Mapping(target = "courierType", source = "type")
    @Mapping(target = "regions", source = "regions")
    @Mapping(target = "workingHours", source = "workingHours")
    CourierDto toDto(Courier courier);

    @InheritConfiguration
    List<CourierDto> toDto(Collection<Courier> couriers);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "type", source = "courierType")
    @Mapping(target = "regions", source = "regions")
    @Mapping(target = "workingHours", source = "workingHours")
    Courier toEntity(CreateCourierDto createCourierDto);

    @InheritConfiguration
    List<Courier> toEntity(Collection<CreateCourierDto> couriers);
}
