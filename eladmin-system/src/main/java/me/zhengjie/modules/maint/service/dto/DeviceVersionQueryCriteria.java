package me.zhengjie.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

@Data
public class DeviceVersionQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "id,hardwareVersion,softwareVersion")
    private String blurry;
}
