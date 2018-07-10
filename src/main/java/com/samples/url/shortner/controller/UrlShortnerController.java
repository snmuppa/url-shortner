package com.samples.url.shortner.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sample.url.shortner.service.UrlShortnerService;
import com.sample.url.shortner.service.UrlValidator;
import com.samples.url.shorner.entity.Url;

@RestController
@RequestMapping("/url-shortner/v1")
public class UrlShortnerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UrlShortnerController.class);

    @Autowired
    private UrlShortnerService urlShortnerService;

    @RequestMapping(value = "/url", method = RequestMethod.POST, consumes = { "application/json" })
    public Url shortenUrl(@RequestBody @Valid final Url url, HttpServletRequest request) throws Exception {
        String longUrl = url.getLongUrl();
        LOGGER.debug("Received url to shorten: " + longUrl);

        if (UrlValidator.validateUrl(longUrl)) {
            String localUrl = request.getRequestURL().toString();
            String shortenedUrl = urlShortnerService.shortenUrl(localUrl, longUrl);
            LOGGER.info("Shortened url to: " + shortenedUrl);
            return new Url(shortenedUrl, longUrl);
        }
        throw new Exception("Please enter a valid URL");
    }

    @RequestMapping(value = "/expand/{urlId}", method = RequestMethod.GET)
    public String expandUrl(@PathVariable String urlId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("Received shortened url to redirect: " + urlId);
        String longUrl = urlShortnerService.getLongUrl(urlId);
        LOGGER.info("Original URL: " + longUrl);
        
        return longUrl;
    }
}
