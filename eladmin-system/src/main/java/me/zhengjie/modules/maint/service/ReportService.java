package me.zhengjie.modules.maint.service;

import me.zhengjie.modules.maint.service.dto.ModelCountDTO;

import java.util.List;

public interface ReportService {

    List<ModelCountDTO> reportDevUsage();

    List<ModelCountDTO> reportRepair();
}
