package ru.yandex.yandexlavka.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class OrderAssignResponse {
    LocalDate date;
    List<CouriersGroupOrders> orders;
}
