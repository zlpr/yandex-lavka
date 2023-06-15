package ru.yandex.yandexlavka.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.yandex.yandexlavka.dto.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.entity.Courier;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OrderRepository;
import ru.yandex.yandexlavka.service.CourierMetaInfoService;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Validated
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourierMetaInfoServiceImpl implements CourierMetaInfoService {
    private final CourierRepository courierRepository;
    private final OrderRepository orderRepository;
    private final CourierMapper courierMapper;

    @Override
    public GetCourierMetaInfoResponse readMetaInfo(Integer courierId, LocalDate start, LocalDate end) {
        var courier = courierRepository.readBy(courierId).orElseThrow(() -> new NotFoundException("Courier ID: %s not found".formatted(courierId)));
        var sum = orderRepository.readTotalCostOfOrdersBy(courierId, start.atStartOfDay(), end.plusDays(1).atStartOfDay());
        var count  = orderRepository.readNumberOfOrdersBy(courierId, start.atStartOfDay(), end.plusDays(1).atStartOfDay());

        if (count == 0) {
            return new GetCourierMetaInfoResponse(courierMapper.toDto(courier), 0, 0);
        }

        int rating = calculateRating(courier,count,start,end);
        int earnings = calculateEarning(courier,sum) ;

        return new GetCourierMetaInfoResponse(courierMapper.toDto(courier), rating, earnings);
    }

    private int calculateEarning(Courier courier, Integer sum) {
        var ratio = courier.getType().getRatio();

        return sum * ratio.getEarning();
    }

    private int calculateRating(Courier courier, Integer count, LocalDate start, LocalDate end) {
        var ratio = courier.getType().getRatio();
        long hours = ChronoUnit.HOURS.between(start,end);

        return (int) (count / (double)hours * ratio.getRating());
    }
}
