package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.modules.maint.service.dto.StockDTO;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

public interface StockService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<StockDTO> queryAll(DeviceStockQueryCriteria criteria, Pageable pageable);
}
