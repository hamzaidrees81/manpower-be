package com.manpower.pos.controller;

import com.manpower.pos.dto.BrandDto;
import com.manpower.pos.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api/brands")
@RequiredArgsConstructor
public class POSBrandController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.save(brandDto));
    }

    @GetMapping
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return ResponseEntity.ok(brandService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Integer id) {
        return ResponseEntity.ok(brandService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBrand(@PathVariable Integer id) {
        brandService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
