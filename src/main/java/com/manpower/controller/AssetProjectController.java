package com.manpower.controller;

import com.manpower.common.Contants;
import com.manpower.model.AssetProject;
import com.manpower.service.AssetProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asset-projects")
public class AssetProjectController {
    private final AssetProjectService assetProjectService;

    public AssetProjectController(AssetProjectService assetProjectService) {
        this.assetProjectService = assetProjectService;
    }

    @GetMapping
    public List<AssetProject> getAllAssetProjects() {
        return assetProjectService.getAllAssetProjects();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssetProject> getAssetProjectById(@PathVariable Integer id) {
        return assetProjectService.getAssetProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{assetId}/{assetProjectStatus}")
    public ResponseEntity<List<AssetProject>> getAssetProjectByAssetId(@PathVariable Integer assetId, @PathVariable Contants.AssetProjectStatus assetProjectStatus) {
        List<AssetProject> assetProjects =  assetProjectService.getAssetProjectByAssetId(assetId, assetProjectStatus);
        return ResponseEntity.ok(assetProjects);
    }

    @PostMapping
    public AssetProject createAssetProject(@RequestBody AssetProject assetProject) {
        return assetProjectService.createAssetProject(assetProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssetProject> updateAssetProject(@PathVariable Integer id, @RequestBody AssetProject assetProject) {
        return ResponseEntity.ok(assetProjectService.updateAssetProject(id, assetProject));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssetProject(@PathVariable Integer id) {
        assetProjectService.deleteAssetProject(id);
        return ResponseEntity.noContent().build();
    }
}
