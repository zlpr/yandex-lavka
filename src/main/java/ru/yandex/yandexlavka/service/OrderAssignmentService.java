package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.dto.OrderAssignResponse;

import java.time.LocalDate;

public interface OrderAssignmentService {

    OrderAssignResponse assign(LocalDate date);
    OrderAssignResponse assignments(LocalDate date, Integer courierId);
}
