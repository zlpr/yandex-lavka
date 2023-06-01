package ru.yandex.yandexlavka.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import ru.yandex.yandexlavka.entity.TimeInterval;

import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface TimeIntervalMapper {

    default String toDto(TimeInterval interval) {
        return interval == null ? null : interval.toString();
    }

    @InheritConfiguration
    List<String> toDto(Collection<TimeInterval> intervals);

    default TimeInterval toEntity(String interval) {
        if (interval == null) {
            return null;
        }

        String[] split = interval.split("-");
        return new TimeInterval(LocalTime.parse(split[0]), LocalTime.parse(split[1]));
    }

    @InheritConfiguration
    List<TimeInterval> toEntity(Collection<String> intervals);

}
