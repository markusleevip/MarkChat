package com.mark.chat.common;

import com.mark.chat.common.enums.ErrorMsgEnum;

import java.io.Serializable;

/**
 * @author Mark
 * @date 2023/4/6
 */
public class Result<T> implements Serializable {
    public static final int SUCCESS_CODE = 0;
    public static final int DEFAULT_FAIL_CODE = 9999;
    public static final String DEFAULT_MESSAGE_SUCCESS = "成功";
    public static final String DEFAULT_MESSAGE_FAIL = "系统错误";
    public static final Result SUCCESSED = new ResultFacade(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS);
    public static final Result FAILED = new ResultFacade(DEFAULT_FAIL_CODE, DEFAULT_MESSAGE_FAIL);
    private Integer code;
    private String message;
    private T data;

    public Result() {
        this((Integer) null, (String) null, (T) null);
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result<T> success() {
        return this.success(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS);
    }

    public Result<T> success(T data) {
        return this.success(SUCCESS_CODE, DEFAULT_MESSAGE_SUCCESS, data);
    }

    public Result<T> success(Integer code, String message) {
        return this.success(code, message, (T) null);
    }

    public Result<T> success(Integer code, String message, T data) {
        return this.setCode(code).setMessage(message).setData(data);
    }


    public Result<T> failed(String message) {
        return this.failed(9999, message, (T) null);
    }

    public Result<T> failed(Integer code, String message) {
        return this.failed(code, message, (T) null);
    }

    public Result<T> failed(Integer code, String message, T data) {
        return this.setCode(code).setMessage(message).setData(data);
    }

    public Result<T> failed(ErrorMsgEnum errorMsgEnum) {
        return this.failed(errorMsgEnum.getCode(),errorMsgEnum.getMsg(),(T) null);
    }


    public Integer getCode() {
        return this.code;
    }

    public Result<T> setCode(Integer code) {
        this.code = code;
        return this;
    }


    public String getMessage() {
        return this.message;
    }

    public Result<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return this.data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        return this;
    }


    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    private static class ResultFacade extends Result<String> {
        private static final String PLACEHOLDER = "";
        private final Integer code;
        private final String message;

        public ResultFacade(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return this.code;
        }

        public Result setCode(Integer code) {
            throw new UnsupportedOperationException("not supported for this operation!");
        }

        public Result setMessage(String message) {
            throw new UnsupportedOperationException("not supported for this operation!");
        }

        public String getMessage() {
            return this.message;
        }

        public Result setData(String data) {
            throw new UnsupportedOperationException("not supported for this operation!");
        }

        public String getData() {
            return "";
        }
    }
}
