package ru.yandex.yandexlavka.dto;

import lombok.Value;

import java.util.List;

@Value
public class GetCouriersResponse {
    List<CourierDto> couriers;
    int limit;
    int offset;

}
