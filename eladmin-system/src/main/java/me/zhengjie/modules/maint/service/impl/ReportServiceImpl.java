package me.zhengjie.modules.maint.service.impl;

import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.maint.repository.ReportRepository;
import me.zhengjie.modules.maint.service.ReportService;
import me.zhengjie.modules.maint.service.dto.ModelCountDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    public List<ModelCountDTO> reportDevUsage() {
        return reportRepository.reportDevUsage();
    }

    @Override
    public List<ModelCountDTO> reportRepair() {
        return reportRepository.reportRepair();
    }
}
