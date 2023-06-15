package ru.yandex.yandexlavka;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ru.yandex.yandexlavka.entity.CourierType;
import ru.yandex.yandexlavka.entity.Limit;
import ru.yandex.yandexlavka.entity.Ratio;
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
        var ct = new CourierType();
        ct.setType(ECourierType.AUTO);
        ct.setRatio(new Ratio(3, 2));
        ct.setLimit(new Limit(40f,7,3,8,4));

        var ct1 = new CourierType();
        ct1.setType(ECourierType.BIKE);
        ct1.setRatio(new Ratio(2, 3));
        ct1.setLimit(new Limit(20f,4,2,12,8));

        var ct2 = new CourierType();
        ct2.setType(ECourierType.FOOT);
        ct2.setRatio(new Ratio(1, 4));
        ct2.setLimit(new Limit(10f,2,1,25,10));

        courierTypeRepository.saveAll(List.of(ct, ct1, ct2));
    }
}
