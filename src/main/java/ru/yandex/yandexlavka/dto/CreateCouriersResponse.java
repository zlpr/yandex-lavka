package ru.yandex.yandexlavka.dto;

import lombok.Value;

import java.util.List;

@Value
public class CreateCouriersResponse {
    List<CourierDto> couriers;
}
