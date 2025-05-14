package com.manpower.controller;

import com.manpower.model.dto.stats.AssetDetailedStatsDTO;
import com.manpower.model.dto.stats.AssetGeneralSummaryDTO;
import com.manpower.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    @GetMapping("/assets")
    public ResponseEntity<List<AssetGeneralSummaryDTO>> getAssetStats() {
        return ResponseEntity.ok(statsService.getAssetsGeneralSummmary());
    }

    @GetMapping("/assets/{assetId}")
    public ResponseEntity<AssetDetailedStatsDTO> getAssetStats(Integer assetId) throws Exception {
        return ResponseEntity.ok(statsService.getAssetStats(assetId));
    }

//    @GetMapping("/projects/{projectId}")
//    public ResponseEntity<AssetDetailedStatsDTO> getProjectAssetStats(Integer projectId) throws Exception {
//        return ResponseEntity.ok(statsService.get(projectId));
//    }




}
