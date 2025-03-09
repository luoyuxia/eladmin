package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RepairRepository extends JpaRepository<Repair, String>,
        JpaSpecificationExecutor<Repair> {
}
