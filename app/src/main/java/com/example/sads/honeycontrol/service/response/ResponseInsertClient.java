package com.example.sads.honeycontrol.service.response;

/**
 * Created by sads on 7/04/17.
 */
public class ResponseInsertClient {
    private boolean success;
    private int id;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
