CREATE TABLE `file_server_configs` (
    `id` INT UNSIGNED AUTO_INCREMENT PRIMARY KEY COMMENT '自增主键',
    `server_name` VARCHAR(100) NOT NULL COMMENT '文件服务器名',
    `file_server_type` VARCHAR(100) NOT NULL COMMENT '文件服务器类型',
    -- 基础连接信息
    `endpoint` VARCHAR(255) NOT NULL COMMENT '服务器地址（域名/IP）',
    `port` INT DEFAULT NULL COMMENT '端口号',
    `user_name` VARCHAR(255) NOT NULL COMMENT '用户名/AccessKey',
    `user_password` VARCHAR(255) NOT NULL COMMENT '密码/SecretKey',
    -- 状态与审计
    `status` TINYINT(1) DEFAULT 1 COMMENT '状态：1启用，0禁用',
    `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    INDEX `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文件服务器连接配置表';

-- 第一行：本地开发环境 MinIO (标准端口 9000)
INSERT INTO `file_server_configs`
(`server_name`, `file_server_type`, `endpoint`, `port`, `user_name`, `user_password`, `status`)
VALUES
    ('Dev_MinIO_Local', 'MINIO', '127.0.0.1', 9000, 'minioadmin', 'minioadmin', 1);

-- 第二行：测试环境 MinIO (带域名的集群)
INSERT INTO `file_server_configs`
(`server_name`, `file_server_type`, `endpoint`, `port`, `user_name`, `user_password`, `status`)
VALUES
    ('Test_Cluster_OSS', 'MINIO', 'minio-test.internal.company.com', 443, 'AKIA_TEST_QWERTY', 'SECRET_PxRfiCYEXAMPLE', 1);

-- 第三行：预发布环境 MinIO (备用节点，暂时禁用)
INSERT INTO `file_server_configs`
(`server_name`, `file_server_type`, `endpoint`, `port`, `user_name`, `user_password`, `status`)
VALUES
    ('Staging_Backup_Node', 'MINIO', '192.168.1.50', 9001, 'ops_user', 'ops_password_2024', 0);