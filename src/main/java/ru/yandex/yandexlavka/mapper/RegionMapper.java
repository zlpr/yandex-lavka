package ru.yandex.yandexlavka.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import ru.yandex.yandexlavka.entity.Region;

import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface RegionMapper {

    default Integer toDto(Region region) {
        return region == null ? null : region.getRegion();
    }

    @InheritConfiguration
    List<Integer> toDto(Collection<Region> regions);

    default Region toEntity(Integer region) {
        return region == null ? null : new Region(region);
    }

    @InheritConfiguration
    List<Region> toEntity(Collection<Integer> regions);

}
