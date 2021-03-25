package com.surveyMonkey.util;

public class ResponseHelper {
    public ResponseHelper(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    private String code;
    private String title;
}
