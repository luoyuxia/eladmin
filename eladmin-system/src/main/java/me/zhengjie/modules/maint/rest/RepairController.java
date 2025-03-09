package me.zhengjie.modules.maint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Repair;
import me.zhengjie.modules.maint.service.RepairService;
import me.zhengjie.modules.maint.service.dto.RepairDTO;
import me.zhengjie.modules.maint.service.dto.RepairQueryCriteria;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@Api(tags = "设备维修")
@RequiredArgsConstructor
@RequestMapping("/api/repair")
public class RepairController {

    private final RepairService repairService;

    @ApiOperation("导出维修数据")
    @GetMapping(value = "/download")
    public void exportRepair(HttpServletResponse response, RepairQueryCriteria criteria) throws IOException {
        repairService.download(repairService.queryAll(criteria), response);
    }


    @ApiOperation(value = "查询维修记录")
    @GetMapping
    public ResponseEntity<PageResult<RepairDTO>> queryRepair(RepairQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(repairService.queryAll(criteria,pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "新增维修")
    @PostMapping
    public ResponseEntity<Object> createRepair(@Validated @RequestBody Repair resources){
        repairService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改维修")
    @PutMapping
    public ResponseEntity<Object> updateRepair(@Validated @RequestBody Repair resources){
        repairService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
