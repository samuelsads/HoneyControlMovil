package com.example.sads.honeycontrol.service.response;

/**
 * Created by sads on 15/04/17.
 */
public class ResponseInsertProduct {
    private boolean success;
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
