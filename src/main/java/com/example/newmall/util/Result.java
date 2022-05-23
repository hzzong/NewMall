package com.example.newmall.util;

public class Result<T> {

    //业务码，比如成功、失败、权限不足等 code，可自行定义

    private int resultCode;
    //返回信息，后端在进行业务处理后返回给前端一个提示信息，可自行定义

    private String message;
    //数据结果，泛型，可以是列表、单个对象、数字、布尔值等

    private T data;


    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
