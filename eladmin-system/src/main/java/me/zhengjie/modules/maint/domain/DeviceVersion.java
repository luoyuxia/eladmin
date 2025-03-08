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
import java.util.Objects;

@Entity
@Getter
@Setter
@Table(name="device_version")
public class DeviceVersion implements Serializable {

    @Id
    @Column(name = "id")
    @ApiModelProperty(value = "ID")
    private String id;

    @Column(name = "hardware_version")
    @ApiModelProperty(value = "硬件版本")
    private String hardwareVersion;

    @Column(name = "software_version")
    @ApiModelProperty(value = "软件版本")
    private String softwareVersion;


    public void copy(DeviceVersion source){
        BeanUtil.copyProperties(source,this, CopyOptions.create().setIgnoreNullValue(true));
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeviceVersion that = (DeviceVersion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
