package org.smart.document.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.smart.document.entity.FileServerConfig;
import org.smart.document.service.FileServerConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 文件服务器配置 Controller
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class FileServerConfigController {

    @Autowired
    private FileServerConfigService fileServerConfigService;

    /**
     * 新增文件服务器配置
     * POST /api/file-server-config
     */
    @PostMapping("/api/file-server-config")
    public Map<String, Object> add(@RequestBody FileServerConfig fileServerConfig) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = fileServerConfigService.save(fileServerConfig);
            result.put("success", success);
            result.put("message", success ? "新增成功" : "新增失败");
            result.put("data", success ? fileServerConfig : null);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "新增失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    /**
     * 更新文件服务器配置
     * PUT /api/file-server-config/{id}
     */
    @PutMapping("/api/file-server-config/{id}")
    public Map<String, Object> update(@PathVariable Integer id, @RequestBody FileServerConfig fileServerConfig) {
        Map<String, Object> result = new HashMap<>();
        try {
            fileServerConfig.setId(id);
            boolean success = fileServerConfigService.updateById(fileServerConfig);
            result.put("success", success);
            result.put("message", success ? "更新成功" : "更新失败");
            result.put("data", success ? fileServerConfig : null);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "更新失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    /**
     * 删除文件服务器配置
     * DELETE /api/file-server-config/{id}
     */
    @DeleteMapping("/api/file-server-config/{id}")
    public Map<String, Object> delete(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = fileServerConfigService.removeById(id);
            result.put("success", success);
            result.put("message", success ? "删除成功" : "删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "删除失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 批量删除文件服务器配置
     * DELETE /api/file-server-config/batch
     */
    @DeleteMapping("/api/file-server-config/batch")
    public Map<String, Object> deleteBatch(@RequestBody List<Integer> ids) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean success = fileServerConfigService.removeByIds(ids);
            result.put("success", success);
            result.put("message", success ? "批量删除成功" : "批量删除失败");
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "批量删除失败：" + e.getMessage());
        }
        return result;
    }

    /**
     * 根据ID查询文件服务器配置
     * GET /api/file-server-config/{id}
     */
    @GetMapping("/api/file-server-config/{id}")
    public Map<String, Object> getById(@PathVariable Integer id) {
        Map<String, Object> result = new HashMap<>();
        try {
            FileServerConfig config = fileServerConfigService.getById(id);
            result.put("success", config != null);
            result.put("message", config != null ? "查询成功" : "数据不存在");
            result.put("data", config);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    /**
     * 查询所有文件服务器配置列表
     * GET /api/file-server-config/list
     */
    @GetMapping("/api/file-server-config/list")
    public Map<String, Object> list() {
        Map<String, Object> result = new HashMap<>();
        try {
            List<FileServerConfig> list = fileServerConfigService.list();
            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", list);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    /**
     * 根据状态查询文件服务器配置列表
     * GET /api/file-server-config/list/status/{status}
     */
    @GetMapping("/api/file-server-config/list/status/{status}")
    public Map<String, Object> listByStatus(@PathVariable Integer status) {
        Map<String, Object> result = new HashMap<>();
        try {
            QueryWrapper<FileServerConfig> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("status", status);
            List<FileServerConfig> list = fileServerConfigService.list(queryWrapper);
            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", list);
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }

    /**
     * 分页查询文件服务器配置
     * GET /api/file-server-config/page
     * 参数：current(当前页), size(每页条数), serverName(服务器名称，可选)
     */
    @GetMapping("/api/file-server-config/page")
    public Map<String, Object> page(
            @RequestParam(defaultValue = "1") Integer current,
            @RequestParam(defaultValue = "10") Integer size,
            @RequestParam(required = false) String serverName) {
        Map<String, Object> result = new HashMap<>();
        try {
            Page<FileServerConfig> page = new Page<>(current, size);
            QueryWrapper<FileServerConfig> queryWrapper = new QueryWrapper<>();

            // 如果传入了服务器名称，进行模糊查询
            if (serverName != null && !serverName.isEmpty()) {
                queryWrapper.like("server_name", serverName);
            }

            // 按创建时间倒序排列
            queryWrapper.orderByDesc("created_at");

            Page<FileServerConfig> pageResult = fileServerConfigService.page(page, queryWrapper);

            result.put("success", true);
            result.put("message", "查询成功");
            result.put("data", pageResult.getRecords());
            result.put("total", pageResult.getTotal());
            result.put("current", pageResult.getCurrent());
            result.put("size", pageResult.getSize());
            result.put("pages", pageResult.getPages());
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "查询失败：" + e.getMessage());
            result.put("data", null);
        }
        return result;
    }
}
