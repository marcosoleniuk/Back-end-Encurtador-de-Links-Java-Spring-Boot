package com.moleniuk.encurtador.repositories;

import com.moleniuk.encurtador.entities.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    UrlEntity findByLongUrl(String longUrl);

    UrlEntity findByShortUrl(String shortUrl);

}
