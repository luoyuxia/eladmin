package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Repair;
import me.zhengjie.modules.maint.repository.RepairRepository;
import me.zhengjie.modules.maint.service.RepairService;
import me.zhengjie.modules.maint.service.dto.RepairDTO;
import me.zhengjie.modules.maint.service.dto.RepairQueryCriteria;
import me.zhengjie.modules.maint.service.mapstruct.RepairMapper;
import me.zhengjie.utils.FileUtil;
import me.zhengjie.utils.PageResult;
import me.zhengjie.utils.PageUtil;
import me.zhengjie.utils.QueryHelp;
import me.zhengjie.utils.ValidationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class RepairServiceImpl implements RepairService {

    private final RepairRepository repairRepository;
    private final RepairMapper repairMapper;

    @Override
    public PageResult<RepairDTO> queryAll(RepairQueryCriteria criteria, Pageable pageable) {
        Page<Repair> page = repairRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(repairMapper::toDto));
    }

    @Override
    public List<RepairDTO> queryAll(RepairQueryCriteria criteria) {
        return repairMapper.toDto(repairRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public RepairDTO findById(String id) {
        Repair repair = repairRepository.findById(id).orElseGet(Repair::new);
        ValidationUtil.isNull(repair.getId(),"Repair","id",id);
        return repairMapper.toDto(repair);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Repair resources) {
        repairRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Repair resources) {
        Repair repair = repairRepository.findById(resources.getId()).orElseGet(Repair::new);
        ValidationUtil.isNull( repair.getId(),"Repair","id",resources.getId());
        repair.copy(resources);
        repairRepository.save(repair);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            repairRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<RepairDTO> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (RepairDTO repairDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("维修编号", repairDTO.getId());
            map.put("设备编号", repairDTO.getDeviceId());
            map.put("维修原因", repairDTO.getReason());
            map.put("状态", repairDTO.getStatus());
            map.put("维修时间", repairDTO.getRepairDate());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }


}
