package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Borrow;
import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.repository.BorrowRepository;
import me.zhengjie.modules.maint.repository.DeviceRepository;
import me.zhengjie.modules.maint.service.BorrowService;
import me.zhengjie.modules.maint.service.dto.BorrowDTO;
import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.modules.maint.service.dto.StockDTO;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BorrowServiceImpl implements BorrowService {

    private final BorrowRepository borrowRepository;

    @Override
    public PageResult<BorrowDTO> queryAll(DeviceStockQueryCriteria criteria, Pageable pageable) {
        List<BorrowDTO> stocks = borrowRepository.getBorrows();
        Page<BorrowDTO> page = new PageImpl<>(stocks, pageable, stocks.size());
        return PageUtil.toPage(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Borrow resources) {
        borrowRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            borrowRepository.deleteById(id);
        }
    }
}
