package com.paladin.currencyconverter.controller;

import com.paladin.currencyconverter.dto.CurrencyConvertDto;
import com.paladin.currencyconverter.dto.ResponseDto;
import com.paladin.currencyconverter.dto.common.ResponseBuilder;
import com.paladin.currencyconverter.exception.CurrencyConverterException;
import com.paladin.currencyconverter.service.CurrencyConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/convert")
public class CurrencyConverterController {

    private final CurrencyConverterService currencyConverterService;

    @Autowired
    public CurrencyConverterController(CurrencyConverterService currencyConverterService) {
        this.currencyConverterService = currencyConverterService;
    }

    @PostMapping
    public ResponseEntity<ResponseDto> convert(@RequestBody CurrencyConvertDto currencyConvertDto){
        ResponseDto res = ResponseBuilder.create();
        try {
            ResponseBuilder.setData(currencyConverterService.convert(
                    currencyConvertDto), res);
            ResponseBuilder.addMessage("OK", res);
            ResponseBuilder.setOperationStatus(true, res);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (CurrencyConverterException e) {
            ResponseBuilder.setOperationStatus(false, res);
            ResponseBuilder.addError(e, res);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(res);
        }
    }


}
