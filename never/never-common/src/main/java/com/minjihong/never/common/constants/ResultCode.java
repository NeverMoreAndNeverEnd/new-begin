package com.minjihong.never.common.constants;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(true,20000,"成功"),
    FAILURE(false,20001,"失败");

    private boolean success;

    private Integer code;

    private String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
