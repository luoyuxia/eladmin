package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.repository.StockRepository;
import me.zhengjie.modules.maint.service.StockService;
import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.modules.maint.service.dto.StockDTO;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    public PageResult<StockDTO> queryAll(DeviceStockQueryCriteria criteria, Pageable pageable) {
        List<StockDTO> stocks = stockRepository.getStock();
        Page<StockDTO> page = new PageImpl<>(stocks, pageable, stocks.size());
        return PageUtil.toPage(page);
    }
}
