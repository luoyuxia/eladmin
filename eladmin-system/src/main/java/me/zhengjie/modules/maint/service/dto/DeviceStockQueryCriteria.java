package me.zhengjie.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

@Data
public class DeviceStockQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "model")
    private String blurry;
}
