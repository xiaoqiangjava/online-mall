package com.xq.learn.exception;

/**
 * 自定义异常
 * @author xiaoqiang
 * @date 2020/3/23 0:03
 */
public class MallException extends RuntimeException
{
    private String errorCode;

    private String errorMessage;

    public MallException(String errorCode, String errorMessage)
    {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
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
}
