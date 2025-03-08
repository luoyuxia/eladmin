package me.zhengjie.modules.maint.repository;

import me.zhengjie.modules.maint.domain.Borrow;

import me.zhengjie.modules.maint.service.dto.BorrowDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BorrowRepository extends JpaRepository<Borrow, String>, JpaSpecificationExecutor<Borrow> {



    @Query(value =
            "select borrow.`device_id` as id, u.`user_name` as userName\n" +
            "from borrow join user u on borrow.user_id = u.user_id;", nativeQuery = true)
    List<BorrowDTO> getBorrows();



}
