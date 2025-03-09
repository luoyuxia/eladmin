package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.domain.Repair;
import me.zhengjie.modules.maint.service.dto.RepairDTO;
import me.zhengjie.modules.maint.service.dto.RepairQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface RepairService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<RepairDTO> queryAll(RepairQueryCriteria criteria, Pageable pageable);

    /**
     * 查询全部数据
     * @param criteria 条件
     * @return /
     */
    List<RepairDTO> queryAll(RepairQueryCriteria criteria);

    /**
     * 根据ID查询
     * @param id /
     * @return /
     */
    RepairDTO findById(String id);


    /**
     * 创建
     * @param resources /
     */
    void create(Repair resources);

    /**
     * 编辑
     * @param resources /
     */
    void update(Repair resources);

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
    void download(List<RepairDTO> queryAll, HttpServletResponse response) throws IOException;
}
