package com.paladin.currencyconverter.service;

import com.paladin.currencyconverter.dto.CurrencyConvertDto;
import com.paladin.currencyconverter.dto.common.ResultDto;
import com.paladin.currencyconverter.exception.CurrencyConverterException;

public interface CurrencyConverterService {

    ResultDto convert(final CurrencyConvertDto currencyConvertDto) throws CurrencyConverterException;

}
