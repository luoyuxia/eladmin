package me.zhengjie.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

@Data
public class DeviceQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "id,model,specification,supplier,status,location")
    private String blurry;
}
