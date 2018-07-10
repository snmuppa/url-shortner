package com.sample.url.shortner.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.url.shortner.repository.UrlRepository;

@Service
public class UrlShortnerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerService.class);

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String localURL, String longUrl) {
        LOGGER.info("Shortening {}", longUrl);
        Long id = urlRepository.incrementId();
        String uniqueID = UrlShortner.createUniqueId(id);
        urlRepository.saveUrl("url:" + id, longUrl);
        String baseString = formatLocalUrlFromShortener(localURL);
        String shortenedURL = baseString + uniqueID;
        return shortenedURL;
    }

    public String getLongUrl(String uniqueId) throws Exception {
        String longUrl = urlRepository.getUrl(uniqueId);
        LOGGER.info("Converting shortened URL back to {}", longUrl);
        return longUrl;
    }

    private String formatLocalUrlFromShortener(String localURL) {
        String[] addressComponents = localURL.split("/");
        // remove the endpoint (last index)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < addressComponents.length - 1; ++i) {
            sb.append(addressComponents[i]);
        }
        sb.append('/');
        return sb.toString();
    }
}
