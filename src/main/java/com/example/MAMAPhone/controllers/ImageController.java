package com.example.MAMAPhone.controllers;

import com.example.MAMAPhone.models.Image;
import com.example.MAMAPhone.repositories.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;

@RestController // реализована логика обработки клиентских запросов
@RequiredArgsConstructor //удаляет конструктор из класса
public class ImageController {
    private final ImageRepository imageRepository;

    @GetMapping("/images/{id}")
    private ResponseEntity<?> getImageById(@PathVariable Long id) { //ResponseEntity — специальный класс для возврата ответов (вернуть клиенту HTTP статус код).
            Image image = imageRepository.findById(id).orElse(null);
            return ResponseEntity.ok().header("fileName", image.getFileName())
                    .contentType(MediaType.valueOf(image.getContentType()))
                    .contentLength(image.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(image.getBytes())));
    }
}
