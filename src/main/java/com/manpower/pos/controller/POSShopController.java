package com.manpower.pos.controller;

import com.manpower.pos.dto.ShopDTO;
import com.manpower.pos.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pos/api/shops")
@RequiredArgsConstructor
public class POSShopController {

    private final ShopService shopService;

    @PostMapping
    public ResponseEntity<Void> createShop(@RequestBody ShopDTO shopDTO) {
        shopService.createShop(shopDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ShopDTO>> getAllShops() {
        return ResponseEntity.ok(shopService.getAllShops());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShopDTO> getShopById(@PathVariable Integer id) {
        return ResponseEntity.ok(shopService.getShopById(id));
    }
}
