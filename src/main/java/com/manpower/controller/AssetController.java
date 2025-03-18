package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.Asset;
import com.manpower.service.AssetService;
import com.manpower.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        this.assetService = assetService;
    }

    @GetMapping("/assetsByCompany")
    public ResponseEntity<List<Asset>> getAllAssetsByCompany() {
        //get company ID from token
        String companyId = (String) SecurityUtil.getClaim(Contants.RateType.Claims.COMPANY_ID.name());
      assert companyId != null;
      return ResponseEntity.ok().body(assetService.getAssetByCompanyId(Integer.parseInt(companyId)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asset> getAssetById(@PathVariable Integer id) {
        return assetService.getAssetById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Asset createAsset(@RequestBody Asset asset) {
        return assetService.createAsset(asset);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asset> updateAsset(@PathVariable Integer id, @RequestBody Asset asset) {
        return ResponseEntity.ok(assetService.updateAsset(id, asset));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsset(@PathVariable Integer id) {
        assetService.deleteAsset(id);
        return ResponseEntity.noContent().build();
    }
}
