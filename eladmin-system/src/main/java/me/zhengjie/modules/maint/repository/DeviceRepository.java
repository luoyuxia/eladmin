package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceRepository extends JpaRepository<Device, String>, JpaSpecificationExecutor<Device> {


}
