package com.paladin.currencyconverter.service;

import com.paladin.currencyconverter.dto.CurrencyConvertDto;
import com.paladin.currencyconverter.dto.CurrencyRatesDto;
import com.paladin.currencyconverter.dto.common.ResultDto;
import com.paladin.currencyconverter.exception.CurrencyConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CurrencyConverterServiceImpl implements CurrencyConverterService {

    private final ApiCallService apiCallService;

    @Autowired
    public CurrencyConverterServiceImpl(ApiCallService apiCallService) {
        this.apiCallService = apiCallService;
    }

    @Override
    public ResultDto convert(final CurrencyConvertDto currencyConvertDto) throws CurrencyConverterException {
        ResultDto returnDto = new ResultDto();

        if(currencyConvertDto != null) {
            HashMap<String, Double> rates = new HashMap<>();
            CurrencyRatesDto res = apiCallService.apiCaller();
            if(rates != null) {
                rates = res.getRates();

                String fromCurrency = "";
                if (currencyConvertDto.getFromCurrency() != null
                        && !currencyConvertDto.getFromCurrency().equals("")
                        && rates.containsKey(currencyConvertDto.getFromCurrency())) {
                    fromCurrency = currencyConvertDto.getFromCurrency();
                } else throw new CurrencyConverterException("Provide value for fromcurrency field doesn't support");

                String toCurrency = "";
                if (currencyConvertDto.getToCurrency() != null
                        && !currencyConvertDto.getToCurrency().equals("")
                        && rates.containsKey(currencyConvertDto.getToCurrency())) {
                    toCurrency = currencyConvertDto.getToCurrency();
                } else throw new CurrencyConverterException("Provide value for tocurrency field doesn't support");

                Double eurFromCurrency = rates.get(fromCurrency);
                Double eurToCurrency = rates.get(toCurrency);
                Double fromToCurrency = eurFromCurrency / eurToCurrency;

                Double finalValue = 0.0;
                Double amount = 0.0;
                if (currencyConvertDto.getAmount() != null && !currencyConvertDto.getAmount().equals(0)) {
                    amount = currencyConvertDto.getAmount();
                } else throw new CurrencyConverterException("Provide value for amount field doesn't support");

                finalValue = amount / fromToCurrency;
                returnDto.setAmount(finalValue);
                returnDto.setCurrency(toCurrency);
                return returnDto;
            }else throw new CurrencyConverterException("Something went wrong with the remote server");
        }else throw new CurrencyConverterException("Request body cannot be empty");
    }






}
