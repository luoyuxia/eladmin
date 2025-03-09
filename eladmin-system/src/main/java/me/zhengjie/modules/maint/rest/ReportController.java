package me.zhengjie.modules.maint.rest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.repository.ReportRepository;
import me.zhengjie.modules.maint.service.dto.ModelCountDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(tags = "报表")
@RequiredArgsConstructor
@RequestMapping("/api/report")
public class ReportController {

    private final ReportRepository reportRepository;

    @ApiOperation(value = "使用报表")
    @GetMapping("/devUsage")
    public ResponseEntity<List<ModelCountDTO>> queryDevUsage(){
        return new ResponseEntity<>(
                reportRepository.reportDevUsage(), HttpStatus.OK);
    }

    @ApiOperation(value = "维修报表")
    @GetMapping("/repair")
    public ResponseEntity<List<ModelCountDTO>> queryRepair(){
        return new ResponseEntity<>(reportRepository.reportRepair(), HttpStatus.OK);
    }
}
