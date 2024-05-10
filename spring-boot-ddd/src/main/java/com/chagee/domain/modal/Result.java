package com.chagee.domain.modal;

public class Result<T> {

    private T data;

    public Result(T data) {
        this.data = data;
    }

    public static Result<Boolean> success(boolean data) {
        return new Result<Boolean>(data);
    }

       public static  Result fail(boolean data) {
        return new Result(data);
    }


}
