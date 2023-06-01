package ru.yandex.yandexlavka.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.yandexlavka.dto.CompleteOrderRequestDto;
import ru.yandex.yandexlavka.dto.CreateOrderRequest;
import ru.yandex.yandexlavka.dto.OrderDto;
import ru.yandex.yandexlavka.service.OrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public List<OrderDto> saveOrders(@RequestBody CreateOrderRequest request){
        return orderService.createOrders(request);
    }

    @PostMapping("complete")
    public List<OrderDto> complete(@RequestBody CompleteOrderRequestDto request){
        return orderService.complete(request);
    }

    @GetMapping
    private List<OrderDto> readAll(@RequestParam(defaultValue = "1") int limit,
                                        @RequestParam(defaultValue = "0") int offset){
        return orderService.readAll(limit,offset);
    }

    @GetMapping("{id}")
    public OrderDto readBy(@PathVariable Integer id){
        return orderService.readBy(id);
    }


}
