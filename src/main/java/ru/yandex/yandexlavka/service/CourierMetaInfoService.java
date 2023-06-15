package ru.yandex.yandexlavka.service;

import ru.yandex.yandexlavka.dto.GetCourierMetaInfoResponse;

import java.time.LocalDate;

public interface CourierMetaInfoService {
    GetCourierMetaInfoResponse readMetaInfo(Integer courierId, LocalDate start, LocalDate end);
}
