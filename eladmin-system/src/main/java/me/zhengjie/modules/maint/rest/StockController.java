package me.zhengjie.modules.maint.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.service.StockService;
import me.zhengjie.modules.maint.service.dto.DeviceStockQueryCriteria;
import me.zhengjie.modules.maint.service.dto.StockDTO;
import me.zhengjie.utils.PageResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "库存信息")
@RequiredArgsConstructor
@RequestMapping("/api/stock")
public class StockController {

    private final StockService stockService; ;

    @ApiOperation(value = "查询库存信息")
    @GetMapping
    public ResponseEntity<PageResult<StockDTO>> queryStock(DeviceStockQueryCriteria criteria,
                                                           Pageable pageable){
        return new ResponseEntity<>(
                stockService.queryAll(criteria,pageable), HttpStatus.OK);
    }
}
