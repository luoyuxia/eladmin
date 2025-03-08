package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.service.dto.StockDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StockRepository extends JpaRepository<Device, String> {



    @Query(value = "SELECT\n" +
            "    d.model as model,\n" +
            "    COUNT(d.id) - COUNT(b.device_id) AS stock\n" +
            "FROM\n" +
            "    device d\n" +
            "LEFT JOIN\n" +
            "    borrow b ON d.id = b.device_id\n" +
            "GROUP BY\n" +
            "    d.model;", nativeQuery = true)
    List<StockDTO> getStock();
}
