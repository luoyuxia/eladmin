package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.repository.DeviceRepository;
import me.zhengjie.modules.maint.service.DeviceService;
import me.zhengjie.modules.maint.service.dto.DeviceDTO;
import me.zhengjie.modules.maint.service.dto.DeviceQueryCriteria;
import me.zhengjie.modules.maint.service.mapstruct.DeviceMapper;
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
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public PageResult<DeviceDTO> queryAll(DeviceQueryCriteria criteria, Pageable pageable) {
        Page<Device> page = deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deviceMapper::toDto));
    }

    @Override
    public List<DeviceDTO> queryAll(DeviceQueryCriteria criteria) {
        return deviceMapper.toDto(deviceRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeviceDTO findById(String id) {
        Device device = deviceRepository.findById(id).orElseGet(Device::new);
        ValidationUtil.isNull(device.getId(),"Device","id",id);
        return deviceMapper.toDto(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(Device resources) {
        deviceRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Device resources) {
        Device device = deviceRepository.findById(resources.getId()).orElseGet(Device::new);
        ValidationUtil.isNull( device.getId(),"Device","id",resources.getId());
        device.copy(resources);
        deviceRepository.save(device);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            deviceRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<DeviceDTO> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeviceDTO deviceDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("设备编号", deviceDTO.getId());
            map.put("型号", deviceDTO.getModel());
            map.put("规格", deviceDTO.getSpecification());
            map.put("购买时间", deviceDTO.getPurchaseDate());
            map.put("供应商", deviceDTO.getSupplier());
            map.put("状态", deviceDTO.getStatus());
            map.put("位置", deviceDTO.getLocation());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
