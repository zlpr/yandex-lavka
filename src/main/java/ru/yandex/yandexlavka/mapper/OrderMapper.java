package ru.yandex.yandexlavka.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingInheritanceStrategy;
import org.mapstruct.ReportingPolicy;
import ru.yandex.yandexlavka.dto.CreateOrderDto;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.entity.Order;

import java.util.Collection;
import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG,
        uses = {RegionMapper.class, TimeIntervalMapper.class})
public interface OrderMapper {
    Order toEntity(CreateOrderDto orderDto);

    OrderDto toDto(Order order);

    @InheritConfiguration
    List<OrderDto> toDto(Collection<Order> couriers);

    @InheritConfiguration
    List<Order> toEntity(Collection<CreateOrderDto> couriers);
}