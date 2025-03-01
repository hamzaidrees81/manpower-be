package com.manpower.controller;

import com.manpower.model.AssetDesignation;
import com.manpower.service.AssetDesignationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-designations")
public class AssetDesignationController {
    private final AssetDesignationService assetDesignationService;

    public AssetDesignationController(AssetDesignationService assetDesignationService) {
        this.assetDesignationService = assetDesignationService;
    }

    @GetMapping
    public List<AssetDesignation> getAllAssetDesignations() {
        return assetDesignationService.getAllAssetDesignations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetDesignation> getAssetDesignationById(@PathVariable Integer id) {
        return assetDesignationService.getAssetDesignationById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public AssetDesignation createAssetDesignation(@RequestBody AssetDesignation assetDesignation) {
        return assetDesignationService.createAssetDesignation(assetDesignation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetDesignation> updateAssetDesignation(@PathVariable Integer id, @RequestBody AssetDesignation assetDesignation) {
        return ResponseEntity.ok(assetDesignationService.updateAssetDesignation(id, assetDesignation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssetDesignation(@PathVariable Integer id) {
        assetDesignationService.deleteAssetDesignation(id);
        return ResponseEntity.noContent().build();
    }
}
