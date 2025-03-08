package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.domain.Borrow;
import me.zhengjie.modules.maint.service.dto.BorrowDTO;
import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface BorrowService {

    /**
     * 分页查询
     * @param criteria 条件
     * @param pageable 分页参数
     * @return /
     */
    PageResult<BorrowDTO> queryAll(DeviceStockQueryCriteria criteria, Pageable pageable);


    /**
     * 创建
     * @param resources /
     */
    void create(Borrow resources);



    /**
     * 删除
     * @param ids /
     */
    void delete(Set<String> ids);
}
