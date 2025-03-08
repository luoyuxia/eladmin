package me.zhengjie.modules.maint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.domain.Device;
import me.zhengjie.modules.maint.domain.DeviceVersion;
import me.zhengjie.modules.maint.service.DeviceVersionService;
import me.zhengjie.modules.maint.service.dto.DeviceDTO;
import me.zhengjie.modules.maint.service.dto.DeviceQueryCriteria;
import me.zhengjie.modules.maint.service.dto.DeviceVersionDTO;
import me.zhengjie.modules.maint.service.dto.DeviceVersionQueryCriteria;
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

@RestController
@Api(tags = "设备版本管理")
@RequiredArgsConstructor
@RequestMapping("/api/deviceVersion")
public class DeviceVersionController {
    
    private final DeviceVersionService deviceVersionService;


    @ApiOperation("导出设备版本数据")
    @GetMapping(value = "/download")
    public void exportServerDeploy(HttpServletResponse response,
                                   DeviceVersionQueryCriteria criteria) throws IOException {
        deviceVersionService.download(deviceVersionService.queryAll
                (criteria), response);
    }


    @ApiOperation(value = "查询设备版本")
    @GetMapping
    public ResponseEntity<PageResult<DeviceVersionDTO>> queryDevice(DeviceVersionQueryCriteria criteria, Pageable pageable){
        return new ResponseEntity<>(deviceVersionService.queryAll(criteria,pageable),
                HttpStatus.OK);
    }


    @ApiOperation(value = "新增设备版本")
    @PostMapping
    public ResponseEntity<Object> createDeviceVersion(@Validated @RequestBody DeviceVersion resources){
        deviceVersionService.create(resources);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @ApiOperation(value = "修改设备版本")
    @PutMapping
    public ResponseEntity<Object> updateDeviceVersion(@Validated @RequestBody DeviceVersion resources){
        deviceVersionService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ApiOperation(value = "删除设备版本")
    @DeleteMapping
    public ResponseEntity<Object> deleteDeviceVersion(@RequestBody Set<String> ids){
        deviceVersionService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
