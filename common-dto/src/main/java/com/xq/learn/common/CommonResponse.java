package com.xq.learn.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;

/**
 * 公共响应实体类
 * @author xiaoqiang
 * @date 2020/3/22 14:57
 */
@JsonPropertyOrder("is_success")
public class CommonResponse implements Serializable
{
    private boolean isSuccess;

    private String message;

    private String errorCode;

    private String errorMessage;

    private Boolean fallback;

    public CommonResponse()
    {
    }

    public CommonResponse(boolean isSuccess, String message)
    {
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public boolean isSuccess()
    {
        return isSuccess;
    }

    @JsonProperty("is_success")
    public void setSuccess(boolean success)
    {
        isSuccess = success;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public String getErrorCode()
    {
        return errorCode;
    }

    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage)
    {
        this.errorMessage = errorMessage;
    }

    public Boolean getFallback()
    {
        return fallback;
    }

    public void setFallback(Boolean fallback)
    {
        this.fallback = fallback;
    }
}
