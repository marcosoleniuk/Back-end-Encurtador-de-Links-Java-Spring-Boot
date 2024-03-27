package com.moleniuk.encurtador.services;


import com.moleniuk.encurtador.dtos.UrlDTO;
import com.moleniuk.encurtador.entities.UrlEntity;
import com.moleniuk.encurtador.repositories.UrlRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final UrlRepository urlRepository;

    @Value("${app.base-url}")
    private String baseUrl;

public UrlDTO createShortUrl(UrlDTO urlDTO) {
    if (urlDTO.longUrl() == null || urlDTO.longUrl().isEmpty()) {
        throw new IllegalArgumentException("URL não pode ficar vazia");
    }
    UrlEntity existingMapping = urlRepository.findByLongUrl(urlDTO.longUrl());
    if (existingMapping != null) {
        return convertEntityToDTO(existingMapping);
    }
    String shortCode = generateRandomShortCode(urlDTO.longUrl());
    UrlEntity newMapping = new UrlEntity();
    newMapping.setLongUrl(urlDTO.longUrl());
    newMapping.setShortUrl(shortCode);
    newMapping.setCreatedAt(String.valueOf(new Date()));
    newMapping.setHits(0L);
    urlRepository.save(newMapping);

    return convertEntityToDTO(newMapping);
}

private UrlDTO convertEntityToDTO(UrlEntity urlEntity) {
    return UrlDTO.builder()
            .id(urlEntity.getId())
            .longUrl(urlEntity.getLongUrl())
            .shortUrl(baseUrl + "/" + urlEntity.getShortUrl())
            .hits(urlEntity.getHits())
            .createdAt(urlEntity.getCreatedAt())
            .build();
}

    public String redirectToLongUrl(String shortUrl) {
        UrlEntity urlEntity = urlRepository.findByShortUrl(shortUrl);
        if (urlEntity == null) {
            throw new IllegalArgumentException("Short URL not found");
        }
        urlEntity.setHits(urlEntity.getHits() + 1);
        urlRepository.save(urlEntity);
        return urlEntity.getLongUrl();
    }

    private String generateRandomShortCode(String longUrl) {
        String allowedCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 6;
        SecureRandom random = new SecureRandom();
        StringBuilder shortCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allowedCharacters.length());
            shortCode.append(allowedCharacters.charAt(randomIndex));
        }
        return shortCode.toString();
    }
}
