package ru.yandex.yandexlavka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.yandex.yandexlavka.entity.CourierType;
import ru.yandex.yandexlavka.model.ECourierType;
import ru.yandex.yandexlavka.repository.CourierTypeRepository;

import java.util.List;

@SpringBootApplication
public class YandexLavkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(YandexLavkaApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(CourierTypeRepository courierTypeRepository) {
        return args -> insertCourierType(courierTypeRepository);
    }

    private void insertCourierType(CourierTypeRepository courierTypeRepository) {
        var ct = new CourierType(); ct.setType(ECourierType.AUTO);
        var ct1 = new CourierType(); ct1.setType(ECourierType.BIKE);
        var ct2 = new CourierType(); ct2.setType(ECourierType.FOOT);

        courierTypeRepository.saveAll(List.of(ct, ct1, ct2));
    }
}
