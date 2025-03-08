package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.domain.ServerDeploy;
import me.zhengjie.modules.maint.service.dto.DeviceDTO;
import me.zhengjie.modules.maint.service.dto.DeviceQueryCriteria;
import me.zhengjie.modules.maint.service.dto.ServerDeployDto;
import me.zhengjie.modules.maint.service.dto.ServerDeployQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DeviceService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<DeviceDTO> queryAll(DeviceQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<DeviceDTO> queryAll(DeviceQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DeviceDTO findById(String id);


    /**
     * 创建
     * @param resources /
     */
    void create(Device resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Device resources);

    /**
     * 删除
     * @param ids /
     */
    void delete(Set<String> ids);

    /**
     * 导出数据
     * @param queryAll /
     * @param response /
     * @throws IOException /
     */
    void download(List<DeviceDTO> queryAll, HttpServletResponse response) throws IOException;
}
