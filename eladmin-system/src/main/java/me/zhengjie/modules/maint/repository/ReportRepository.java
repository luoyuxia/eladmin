package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.Repair;
import me.zhengjie.modules.maint.service.dto.ModelCountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportRepository extends JpaRepository<Repair, String>,
        JpaSpecificationExecutor<Repair> {


    @Query(value = "select d.model as model, count(1) as count\n" +
            "    from borrow b join device d on d.id = b.device_id\n" +
            "group by d.model", nativeQuery = true)
    List<ModelCountDTO> reportDevUsage();

    @Query(value ="select d.model as model, count(1) as count\n" +
            "from repair join device d on d.id = repair.device_id", nativeQuery = true)
    List<ModelCountDTO> reportRepair();

}
