package com.paladin.currencyconverter.service;

import com.paladin.currencyconverter.dto.CurrencyRatesDto;
import com.paladin.currencyconverter.exception.CurrencyConverterException;
import com.paladin.currencyconverter.manager.FixerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class ApiCallServiceImpl implements ApiCallService {

    private final FixerManager fixerManager;

    @Autowired
    public ApiCallServiceImpl(FixerManager fixerManager) {
        this.fixerManager = fixerManager;
    }

    @Override
    @Cacheable(value = "currency-converter", key = "'currency-rates'")
    public CurrencyRatesDto apiCaller() throws CurrencyConverterException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(fixerManager.getBaseURL())
                .queryParam("access_key", fixerManager.getApiKey());
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<CurrencyRatesDto> res = restTemplate
                .exchange(builder.toUriString(), HttpMethod.GET, entity, CurrencyRatesDto.class);
        return res.getBody();
    }
}
