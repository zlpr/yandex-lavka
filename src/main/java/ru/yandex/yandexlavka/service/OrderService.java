package ru.yandex.yandexlavka.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.yandex.yandexlavka.dto.CompleteOrder;
import ru.yandex.yandexlavka.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.dto.CreateOrderRequest;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.exception.BadRequestException;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.mapper.OrderMapper;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OffsetBasedPageRequest;
import ru.yandex.yandexlavka.repository.OrderRepository;

import java.util.List;
@Validated
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public List<OrderDto> createOrders(@Valid CreateOrderRequest request) {
        var toSaveOrders = orderMapper.toEntity(request.getOrders());
        var entities = orderRepository.saveAll(toSaveOrders);

        return orderMapper.toDto(entities);
    }

    public OrderDto readBy(@NotNull Integer id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("Order ID: %s not found".formatted(id)));

        return orderMapper.toDto(order);
    }

    public List<OrderDto> readAll(@Min(1) int limit, @Min(0) int offset) {
        var pageable = new OffsetBasedPageRequest(offset, limit, Sort.unsorted());
        var slice = orderRepository.readAllBy(pageable);

        return orderMapper.toDto(slice.toList());
    }

    @Transactional
    public List<OrderDto> complete(@Valid CompleteOrderRequestDto request) {
        return request.getCompleteInfo().stream()
                .map(this::complete)
                .toList();
    }

    private OrderDto complete(CompleteOrder completeOrder) {
        var order = orderRepository
                .readByIdAndCourierId(completeOrder.getOrderId(), completeOrder.getCourierId())
                .orElseThrow(() -> new BadRequestException("Bad Request"));
        if (order.getCompletedTime() == null) {
            order.setCompletedTime(completeOrder.getCompletedTime());
        }

        return orderMapper.toDto(order);
    }


}
