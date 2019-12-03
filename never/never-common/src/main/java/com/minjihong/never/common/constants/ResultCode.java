package com.minjihong.never.common.constants;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(true,20000,"成功"),
    FAILURE(false,20001,"失败"),
    FILE_UPLOAD_ERROR(false,21004,"文件上传错误"),
    EXCEL_IMPORT_ERROR(false,21005,"excel数据导入错误");

    private boolean success;

    private Integer code;

    private String message;

    ResultCode(boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
