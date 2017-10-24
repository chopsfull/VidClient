package com.example.chopsfull_.test_task_tereshchuk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class APIError {

    @SerializedName("status")
    @Expose
    private boolean status;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("error")
    @Expose
    private String error;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
