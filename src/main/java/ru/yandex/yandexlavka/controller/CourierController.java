package ru.yandex.yandexlavka.controller;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.dto.CreateCouriersResponse;
import ru.yandex.yandexlavka.dto.GetCourierMetaInfoResponse;
import ru.yandex.yandexlavka.dto.GetCouriersResponse;
import ru.yandex.yandexlavka.dto.OrderAssignResponse;
import ru.yandex.yandexlavka.service.CourierMetaInfoService;
import ru.yandex.yandexlavka.service.CourierService;
import ru.yandex.yandexlavka.service.OrderAssignmentService;

import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("couriers")

public class CourierController {
    private final CourierService courierService;
    private final CourierMetaInfoService courierMetaInfoService;
    private final OrderAssignmentService orderAssignmentService;
    @RateLimiter(name = "couriers.saveCouriers")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public CreateCouriersResponse saveCouriers(@RequestBody CreateCourierRequest request) {
        var couriers = courierService.createCouriers(request);

        return new CreateCouriersResponse(couriers);
    }
    @RateLimiter(name = "couriers.readBy")
    @GetMapping("{id}")
    public CourierDto readBy(@PathVariable Integer id) {
        return courierService.readBy(id);
    }
    @RateLimiter(name = "couriers")
    @GetMapping
    public GetCouriersResponse readAll(@RequestParam(defaultValue = "1") int limit,
                                       @RequestParam(defaultValue = "0") int offset) {
        var couriers = courierService.readAll(limit, offset);

        return new GetCouriersResponse(couriers, limit, offset);
    }
    @RateLimiter(name = "couriers")
    @GetMapping("meta-info/{courierId}")
    public GetCourierMetaInfoResponse readMetaInfo(@PathVariable Integer courierId,
                                                   @RequestParam LocalDate startDate,
                                                   @RequestParam LocalDate endDate) {
        return courierMetaInfoService.readMetaInfo(courierId, startDate, endDate);
    }

    @RateLimiter(name = "couriers.assignments")
    @GetMapping("assignments")
    private OrderAssignResponse readAssignedOrdersBy(@RequestParam Integer courierId, @RequestParam LocalDate date){
        return orderAssignmentService.readAssignedOrdersBy(courierId, date);
    }


}
