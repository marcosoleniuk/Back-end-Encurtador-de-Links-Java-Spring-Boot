package com.moleniuk.encurtador.dtos;

import lombok.Builder;

@Builder
public record UrlDTO(
    Long id,
    String longUrl,
    String shortUrl,
    Long hits,
    String createdAt
) {
}
