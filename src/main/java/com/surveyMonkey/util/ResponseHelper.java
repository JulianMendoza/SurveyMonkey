package com.surveyMonkey.util;

public class ResponseHelper {
    public ResponseHelper(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
}
