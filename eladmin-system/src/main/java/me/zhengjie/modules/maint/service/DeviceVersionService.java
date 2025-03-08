package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.domain.DeviceVersion;
import me.zhengjie.modules.maint.service.dto.DeviceVersionDTO;
import me.zhengjie.modules.maint.service.dto.DeviceVersionQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface DeviceVersionService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<DeviceVersionDTO> queryAll(DeviceVersionQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<DeviceVersionDTO> queryAll(DeviceVersionQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    DeviceVersionDTO findById(String id);


    /**
     * 创建
     * @param resources /
     */
    void create(DeviceVersion resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(DeviceVersion resources);

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
    void download(List<DeviceVersionDTO> queryAll, HttpServletResponse response) throws IOException;
}
