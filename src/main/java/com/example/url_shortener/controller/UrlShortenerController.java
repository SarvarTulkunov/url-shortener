package com.example.url_shortener.controller;

import com.example.url_shortener.dto.MappingResponse;
import com.example.url_shortener.service.UrlShortenerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortener")
public class UrlShortenerController {

    private final UrlShortenerService urlShortenerService;

    public UrlShortenerController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    @GetMapping
    public ResponseEntity<MappingResponse> getLongUrl(@RequestParam String shortUrl) {
        return ResponseEntity.ok(urlShortenerService.getLongUrl(shortUrl));
    }

    @PostMapping("/shorten")
    public ResponseEntity<MappingResponse> shortenUrl(@RequestParam String longUrl) {
        return ResponseEntity.ok(urlShortenerService.generateShortUrl(longUrl));
    }

}