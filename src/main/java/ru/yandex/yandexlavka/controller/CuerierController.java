package ru.yandex.yandexlavka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.dto.CreateCouriersResponse;
import ru.yandex.yandexlavka.dto.GetCouriersResponse;
import ru.yandex.yandexlavka.service.CourierService;


@RestController
@RequiredArgsConstructor
@RequestMapping("couriers")
public class CuerierController {
    private final CourierService courierService;

    @PostMapping
    public CreateCouriersResponse saveCouriers(@RequestBody CreateCourierRequest request){
        var couriers = courierService.createCouriers(request);

        return new CreateCouriersResponse(couriers);
    }

    @GetMapping("{id}")
    public CourierDto readBy(@PathVariable Integer id){
        return courierService.readBy(id);
    }

    @GetMapping
    public GetCouriersResponse readAll(@RequestParam(defaultValue = "1") int limit,
                                         @RequestParam(defaultValue = "0") int offset){
        var couriers = courierService.readAll(limit,offset);

        return new GetCouriersResponse(couriers, limit, offset);
    }


}
