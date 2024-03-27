package com.moleniuk.encurtador.controllers;


import com.moleniuk.encurtador.dtos.UrlDTO;
import com.moleniuk.encurtador.services.UrlService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
public class UrlController {

    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<UrlDTO> createShortner(@RequestBody @Valid UrlDTO urlDTO) {
        UrlDTO urlSave = urlService.createShortUrl(urlDTO);
        return new ResponseEntity<>(urlSave, HttpStatus.CREATED);
    }

    @GetMapping("/{shortUrl}")
    @ResponseStatus(HttpStatus.FOUND)
    public void redirectToLongUrl(@PathVariable String shortUrl, HttpServletResponse response) {
        String longUrl = urlService.redirectToLongUrl(shortUrl);
        response.setHeader("Location", longUrl);
    }

}
