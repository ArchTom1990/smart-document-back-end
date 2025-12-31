package org.smart.document.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 文件服务器配置表
 */
@Data
@TableName("file_server_configs")
public class FileServerConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 文件服务器名
     */
    @TableField("server_name")
    private String serverName;

    /**
     * 文件服务器类型
     */
    @TableField("file_server_type")
    private String fileServerType;

    /**
     * 服务器地址（域名/IP）
     */
    @TableField("endpoint")
    private String endpoint;

    /**
     * 端口号
     */
    @TableField("port")
    private Integer port;

    /**
     * 用户名/AccessKey
     */
    @TableField("user_name")
    private String userName;

    /**
     * 密码/SecretKey
     */
    @TableField("user_password")
    private String userPassword;

    /**
     * 状态：1启用，0禁用
     */
    @TableField("status")
    private Integer status;

    /**
     * 创建时间
     */
    @TableField("created_at")
    private LocalDateTime createdAt;

    /**
     * 更新时间
     */
    @TableField("updated_at")
    private LocalDateTime updatedAt;
}
