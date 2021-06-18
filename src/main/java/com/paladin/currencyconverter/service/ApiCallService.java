package com.paladin.currencyconverter.service;

import com.paladin.currencyconverter.dto.CurrencyRatesDto;
import com.paladin.currencyconverter.exception.CurrencyConverterException;

public interface ApiCallService {

    public CurrencyRatesDto apiCaller() throws CurrencyConverterException;
}
