package ru.yandex.yandexlavka.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.yandex.yandexlavka.dto.CourierDto;
import ru.yandex.yandexlavka.dto.CreateCourierRequest;
import ru.yandex.yandexlavka.exception.NotFoundException;
import ru.yandex.yandexlavka.mapper.CourierMapper;
import ru.yandex.yandexlavka.repository.CourierRepository;
import ru.yandex.yandexlavka.repository.OffsetBasedPageRequest;

import java.util.List;
@Validated
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CourierService {

    private final CourierRepository courierRepository;
    private final CourierMapper courierMapper;

    @Transactional
    public List<CourierDto> createCouriers(@Valid CreateCourierRequest request) {
        var toSaveCouriers = courierMapper.toEntity(request.getCouriers());
        var entities = courierRepository.saveAll(toSaveCouriers);

        return courierMapper.toDto(entities);
    }

    public CourierDto readBy(@NotNull Integer id) {
        var courier = courierRepository.findById(id).orElseThrow(() -> new NotFoundException("Courier ID: %s not found"));

        return courierMapper.toDto(courier);
    }

    public List<CourierDto> readAll(@Min(1) int limit, @Min(0) int offset) {
        var pageable = new OffsetBasedPageRequest(offset, limit, Sort.unsorted());
        var slice = courierRepository.readAllBy(pageable);

        return courierMapper.toDto(slice.toList());
    }


}
