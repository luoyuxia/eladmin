package me.zhengjie.modules.maint.service.mapstruct;

import me.zhengjie.base.BaseMapper;
import me.zhengjie.modules.maint.domain.DeviceVersion;
import me.zhengjie.modules.maint.service.dto.DeviceVersionDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DeviceVersionMapper  extends BaseMapper<DeviceVersionDTO, DeviceVersion> {
}
