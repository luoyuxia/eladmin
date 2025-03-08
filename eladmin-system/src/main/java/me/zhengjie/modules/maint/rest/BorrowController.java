package me.zhengjie.modules.maint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Borrow;
import me.zhengjie.modules.maint.service.BorrowService;
import me.zhengjie.modules.maint.service.dto.BorrowDTO;
import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@Api(tags = "借用信息")
@RequiredArgsConstructor
@RequestMapping("/api/borrow")
public class BorrowController {
    private final BorrowService borrowService;

    @ApiOperation(value = "查询借用信息")
    @GetMapping
    public ResponseEntity<PageResult<BorrowDTO>> queryBorrow(DeviceStockQueryCriteria criteria,
                                                            Pageable pageable){
        return new ResponseEntity<>(
                borrowService.queryAll(criteria,pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "新增借用信息")
    @PostMapping
    public ResponseEntity<Object> createBorrow(@Validated @RequestBody Borrow resources){
        borrowService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "新增借用信息")
    @PutMapping
    public ResponseEntity<Object> putBorrow(@Validated @RequestBody Borrow resources){
        borrowService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }



    @ApiOperation(value = "删除借用信息")
    @DeleteMapping
    public ResponseEntity<Object> deleteBorrow(@RequestBody Set<String> ids){
        borrowService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
