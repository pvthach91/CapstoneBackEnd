package com.pvthach.capstone.message.response;

public class Response{
  public static <T> ApiResponse<T> successResult(T data) {
    ApiResponse<T> result = new ApiResponse<>();

    result.setData(data);

    return result;
  }

  public static <T> ApiResponse<T> failedResult(String messege) {
    ApiResponse<T> result = new ApiResponse<>();
    result.setSuccess(false);
    result.setMessage(messege);
    result.setData(null);

    return result;
  }
}
