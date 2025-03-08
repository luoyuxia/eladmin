package me.zhengjie.modules.maint.domain;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Table(name="borrow")
public class Borrow implements Serializable {

    @Id
    @Column(name = "device_id")
    @ApiModelProperty(value = "DeviceId")
    private String deviceId;


    @Column(name = "user_id")
    @ApiModelProperty(value = "UserId")
    private String userId;

    public void copy(Borrow source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }
}
