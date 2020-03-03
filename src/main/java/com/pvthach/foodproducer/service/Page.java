package com.pvthach.foodproducer.service;


import java.io.Serializable;

/**
 * Created by THACH-PC
 */

public class Page<T> implements Serializable {

    private Integer current;

    private Long total;

    private T data;

    public Page() {}

    public Integer getCurrent() {
        return current;
    }

    public void setCurrent(Integer current) {
        this.current = current;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
