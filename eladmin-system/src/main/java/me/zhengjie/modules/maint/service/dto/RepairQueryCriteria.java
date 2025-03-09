package me.zhengjie.modules.maint.service.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import me.zhengjie.annotation.Query;

@Data
public class RepairQueryCriteria {

    @ApiModelProperty(value = "模糊查询")
    @Query(blurry = "id,deviceId,reason,status,repairDate")
    private String blurry;

}
