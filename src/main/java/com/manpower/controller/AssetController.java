package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.mapper.AssetMapper;
import com.manpower.model.dto.AssetDTO;
import com.manpower.service.AssetService;
import com.manpower.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/assetsByCompany")
    public ResponseEntity<List<AssetDTO>> getAllAssetsByCompany() {
        //get company ID from token
        String companyId = (String) SecurityUtil.getClaim(Contants.RateType.Claims.COMPANY_ID.name());
      assert companyId != null;

      Integer companyIdInt =Integer.parseInt(companyId);
        List<AssetDTO> list = assetService.getAssetByCompanyId(companyIdInt).stream().map(AssetMapper::toDTO).collect(Collectors.toList());
      return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDTO> getAssetById(@PathVariable Integer id) {
        return assetService.getAssetById(id)
          .map(asset -> ResponseEntity.ok(AssetMapper.toDTO(asset))) // Map Asset to AssetDTO
          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssetDTO createAsset(@RequestBody AssetDTO asset) {
        return assetService.createAsset(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDTO> updateAsset(@PathVariable Integer id, @RequestBody AssetDTO asset) {
        return ResponseEntity.ok(assetService.updateAsset(id, asset));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
