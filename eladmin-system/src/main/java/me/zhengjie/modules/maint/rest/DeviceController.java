package me.zhengjie.modules.maint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.annotation.Log;
import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.service.DeviceService;
import me.zhengjie.modules.maint.service.dto.DeviceDTO;
import me.zhengjie.modules.maint.service.dto.DeviceQueryCriteria;
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

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * @author zhanghouying
 * @date 2019-08-24
 */
@RestController
@Api(tags = "设备管理")
@RequiredArgsConstructor
@RequestMapping("/api/device")
public class DeviceController {

    private final DeviceService deviceService; ;

    @ApiOperation("导出设备数据")
    @GetMapping(value = "/download")
    public void exportServerDeploy(HttpServletResponse response, DeviceQueryCriteria criteria) throws IOException {
        deviceService.download(deviceService.queryAll(criteria), response);
    }


    @ApiOperation(value = "查询设备")
    @GetMapping
    public ResponseEntity<PageResult<DeviceDTO>> queryDevice(DeviceQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deviceService.queryAll(criteria,pageable), HttpStatus.OK);
    }


    @ApiOperation(value = "新增设备")
    @PostMapping
    public ResponseEntity<Object> createDevice(@Validated @RequestBody Device resources){
        deviceService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改设备")
    @PutMapping
    public ResponseEntity<Object> updateDevice(@Validated @RequestBody Device resources){
        deviceService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除设备")
    @DeleteMapping
    public ResponseEntity<Object> deleteDevice(@RequestBody Set<String> ids){
        deviceService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
