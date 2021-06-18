package com.paladin.currencyconverter.dto;

import com.paladin.currencyconverter.dto.common.ErrorMessage;
import com.paladin.currencyconverter.dto.common.SuccessMessage;

import java.util.List;

public class ResponseDto {

    private boolean status;
    private Iterable<?> data;
    private List<ErrorMessage> errors;
    private List<SuccessMessage> successMessages;


    public ResponseDto() {
    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Iterable<?> getData() {
        return data;
    }

    public void setData(Iterable<?> data) {
        this.data = data;
    }

    public List<ErrorMessage> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorMessage> errors) {
        this.errors = errors;
    }

    public List<SuccessMessage> getSuccessMessages() {
        return successMessages;
    }

    public void setSuccessMessages(List<SuccessMessage> successMessages) {
        this.successMessages = successMessages;
    }
}
