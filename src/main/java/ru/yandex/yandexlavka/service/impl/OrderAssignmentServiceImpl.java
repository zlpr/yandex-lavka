package ru.yandex.yandexlavka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.yandexlavka.dto.OrderAssignResponse;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.OrderAssignmentService;

import java.time.LocalDate;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderAssignmentServiceImpl implements OrderAssignmentService {
    private final OrderRepository orderRepository;
    private final CourierRepository courierRepository;

    @Override
    public OrderAssignResponse assignOrders(LocalDate date) {
        if (date == null) {
            date = LocalDate.now();
        }
        return null;
    }

    @Override
    public OrderAssignResponse readAssignedOrdersBy(Integer courierId, LocalDate date) {
        return null;
    }
}
