package org.smart.document;

import lombok.Data;

@Data
public class LoginResponse {

    private String status;

    /**
     * 登录类型，如 'account', 'mobile' 等
     */
    private String type;

    /**
     * 当前用户权限，Ant Design Pro 默认为 'user' 或 'admin'
     * 如果有多角色，也可以定义为 String[] 数组
     */
    private String currentAuthority;

    private String token;
}
