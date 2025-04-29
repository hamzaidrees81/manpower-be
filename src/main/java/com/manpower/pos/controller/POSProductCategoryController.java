package com.manpower.pos.controller;

import com.manpower.pos.dto.ProductCategoryDto;
import com.manpower.pos.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api/product-categories")
@RequiredArgsConstructor
public class POSProductCategoryController {

    private final ProductCategoryService productCategoryService;

    @PostMapping
    public ResponseEntity<ProductCategoryDto> createProductCategory(@RequestBody ProductCategoryDto productCategoryDto) {
        return ResponseEntity.ok(productCategoryService.save(productCategoryDto));
    }

    @GetMapping
    public ResponseEntity<List<ProductCategoryDto>> getAllProductCategories() {
        return ResponseEntity.ok(productCategoryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductCategoryDto> getProductCategoryById(@PathVariable Integer id) {
        return ResponseEntity.ok(productCategoryService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable Integer id) {
        productCategoryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
