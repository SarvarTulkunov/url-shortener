package com.example.url_shortener.service;

import com.example.url_shortener.dto.MappingResponse;
import com.example.url_shortener.model.UrlMapping;
import com.example.url_shortener.model.UrlMappingRepository;
import com.example.url_shortener.util.UrlShortenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UrlShortenerService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    private final UrlMappingRepository urlMappingRepository;

    private static final String URL_PREFIX = "http://short.ly/";
    private static final String REDIS_KEY_PREFIX = "urlShortener:";

    public UrlShortenerService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public MappingResponse generateShortUrl(String longUrl) {

        // 1. Check if the short URL exists in Redis
        String redisKey = REDIS_KEY_PREFIX + longUrl;
        String shortUrlKey = redisTemplate.opsForValue().get(redisKey);

        if (shortUrlKey != null) {
            return new MappingResponse(URL_PREFIX + shortUrlKey);
        }

        // 2. Check if the long URL exists in MongoDB
        Optional<UrlMapping> existingMapping = urlMappingRepository.findByLongUrl(longUrl);
        if (existingMapping.isPresent()) {
            shortUrlKey = existingMapping.get().getShortUrl();
            // Cache the result in Redis
            redisTemplate.opsForValue().set(redisKey, shortUrlKey);
            return new MappingResponse(URL_PREFIX + shortUrlKey);
        }

        // 3. Generate a new unique short URL
        long uniqueId = generateUniqueId(); // Example: Use timestamp for uniqueness
        shortUrlKey = UrlShortenUtil.encodeToBase62(uniqueId);

        // 4. Save the mapping to MongoDB
        saveNewUrlMapping(shortUrlKey, longUrl);

        // 5. Save the mapping to Redis
        redisTemplate.opsForValue().set(redisKey, shortUrlKey);
        redisTemplate.opsForValue().set(REDIS_KEY_PREFIX + shortUrlKey, longUrl); // Reverse mapping

        return new MappingResponse(URL_PREFIX + shortUrlKey);
    }

    public MappingResponse getLongUrl(String shortUrl) {

        // Remove the prefix if it exists
        String shortUrlKey = shortUrl.replace(URL_PREFIX, "");

        // Check in Redis
        String longUrl = redisTemplate.opsForValue().get(REDIS_KEY_PREFIX + shortUrlKey);
        if (longUrl != null) {
            return new MappingResponse(shortUrl, longUrl);
        }

        // Check in MongoDB
        Optional<UrlMapping> optional = urlMappingRepository.findById(shortUrlKey);
        if (optional.isPresent()) {
            longUrl = optional.get().getLongUrl();

            // Save in Redis for future lookups
            redisTemplate.opsForValue().set(shortUrlKey, longUrl);

            return new MappingResponse(shortUrl, longUrl);
        }

        // Return null if not found
        return new MappingResponse(shortUrl, "");
    }

    public long generateUniqueId(){
        return redisTemplate.opsForValue().increment("urlShortener:uniqueId");
    }

    private void saveNewUrlMapping(String shorUrlKey, String longUrl){
        UrlMapping urlMapping = new UrlMapping();
        urlMapping.setShortUrl(shorUrlKey);
        urlMapping.setLongUrl(longUrl);
        urlMappingRepository.save(urlMapping);
    }
}
