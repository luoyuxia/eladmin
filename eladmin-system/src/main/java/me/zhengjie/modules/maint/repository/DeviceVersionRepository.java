package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.DeviceVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DeviceVersionRepository extends JpaRepository<DeviceVersion, String>, JpaSpecificationExecutor<DeviceVersion> {
}
