package ru.yandex.yandexlavka.integration.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.yandex.yandexlavka.integration.IntegrationTestBase;

import static org.hamcrest.Matchers.*;
import static java.nio.charset.StandardCharsets.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@RequiredArgsConstructor
class CourierControllerTest extends IntegrationTestBase {

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    @Test
    void saveCouriers() throws Exception {
        var file = new ClassPathResource("json/saveCouriers.json");

        mockMvc.perform(post("/couriers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(file.getContentAsString(UTF_8)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.couriers",hasSize(9)));

    }

    @Test
    void readBy() {
    }

    @Test
    void readAll() {
    }

    @Test
    void readMetaInfo() {
    }
}