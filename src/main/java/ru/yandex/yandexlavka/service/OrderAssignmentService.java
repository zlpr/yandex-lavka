package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.dto.OrderAssignResponse;

import java.time.LocalDate;

public interface OrderAssignmentService {

    OrderAssignResponse assignOrders(LocalDate date);
    OrderAssignResponse readAssignedOrdersBy(Integer courierId, LocalDate date);
}
