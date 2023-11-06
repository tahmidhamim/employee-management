package com.techlearn.employeemanagement.dto;

public class CountResponse {
    private long count;

    public CountResponse(long count) {
        setCount(count);
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
