package com.cars24.csms.data.resp;

import lombok.Data;
import lombok.Setter;

@Data
public class APIResponse {
    private boolean success;
    // Ensure you have the setter method for statuscode, and it's named correctly.
    @Setter
    private int statuscode;
    private String message;
    private String service;
    private Object data; // To hold error map or any other data if necessary

}
