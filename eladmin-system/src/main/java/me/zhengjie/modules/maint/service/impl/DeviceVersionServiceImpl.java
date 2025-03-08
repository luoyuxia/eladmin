package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.DeviceVersion;
import me.zhengjie.modules.maint.repository.DeviceVersionRepository;
import me.zhengjie.modules.maint.service.DeviceVersionService;
import me.zhengjie.modules.maint.service.dto.DeviceVersionDTO;
import me.zhengjie.modules.maint.service.dto.DeviceVersionQueryCriteria;
import me.zhengjie.modules.maint.service.mapstruct.DeviceVersionMapper;
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
public class DeviceVersionServiceImpl implements DeviceVersionService {

    private final DeviceVersionRepository deviceVersionRepository;
    private final DeviceVersionMapper deviceVersionMapper;

    @Override
    public PageResult<DeviceVersionDTO> queryAll(DeviceVersionQueryCriteria criteria, Pageable pageable) {
        Page<DeviceVersion> page = deviceVersionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder),pageable);
        return PageUtil.toPage(page.map(deviceVersionMapper::toDto));
    }

    @Override
    public List<DeviceVersionDTO> queryAll(DeviceVersionQueryCriteria criteria) {
        return deviceVersionMapper.toDto(deviceVersionRepository.findAll((root, criteriaQuery, criteriaBuilder) -> QueryHelp.getPredicate(root,criteria,criteriaBuilder)));
    }

    @Override
    public DeviceVersionDTO findById(String id) {
        DeviceVersion deviceVersion = deviceVersionRepository.findById(id).orElseGet(DeviceVersion::new);
        ValidationUtil.isNull(deviceVersion.getId(),"DeviceVersion","id",id);
        return deviceVersionMapper.toDto(deviceVersion);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(DeviceVersion resources) {
        deviceVersionRepository.save(resources);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(DeviceVersion resources) {
        DeviceVersion deviceVersion = deviceVersionRepository.findById(resources.getId()).orElseGet(DeviceVersion::new);
        ValidationUtil.isNull( deviceVersion.getId(),"DeviceVersion","id",resources.getId());
        deviceVersion.copy(resources);
        deviceVersionRepository.save(deviceVersion);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Set<String> ids) {
        for (String id : ids) {
            deviceVersionRepository.deleteById(id);
        }
    }

    @Override
    public void download(List<DeviceVersionDTO> queryAll, HttpServletResponse response) throws IOException {
        List<Map<String, Object>> list = new ArrayList<>();
        for (DeviceVersionDTO deviceVersionDTO : queryAll) {
            Map<String,Object> map = new LinkedHashMap<>();
            map.put("设备编号", deviceVersionDTO.getId());
            map.put("硬件版本", deviceVersionDTO.getHardwareVersion());
            map.put("软件版本", deviceVersionDTO.getSoftwareVersion());
            list.add(map);
        }
        FileUtil.downloadExcel(list, response);
    }
}
