package org.smart.document;

import lombok.Data;

@Data
public class LoginRequest {

    private Boolean autoLogin;
    private String username;
    private String password;
    private String type;
}
