package com.swapnil.appointment_service.config;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Component;

@Component
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Exception decode(String methodKey, Response response) {

        try {

            InputStream body = response.body().asInputStream();

            JsonNode jsonNode = objectMapper.readTree(body);

            String message = jsonNode.has("message")
                    ? jsonNode.get("message").asText()
                    : "Microservice communication failed.";

            switch (response.status()) {

                case 404:
                    return new RuntimeException(message);

                case 503:
                    return new RuntimeException(message);

                default:
                    return new RuntimeException(message);

            }

        } catch (IOException e) {

            return defaultErrorDecoder.decode(methodKey, response);

        }

    }
}