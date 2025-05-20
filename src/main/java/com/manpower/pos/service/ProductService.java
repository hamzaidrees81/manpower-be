package com.manpower.pos.service;

import com.manpower.pos.dto.ProductDto;
import com.manpower.pos.enums.AliveStatus;
import com.manpower.pos.mapper.ProductMapper;
import com.manpower.pos.model.Product;
import com.manpower.pos.repository.ProductRepository;
import com.manpower.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findByCompany_Id(SecurityUtil.getCompanyClaim());
        return products.stream()
                .map(productMapper::toDto)
                .toList();
    }

    public Optional<ProductDto> getProductById(Integer id) {
        return productRepository.findByIdAndCompany_Id(id, SecurityUtil.getCompanyClaim())
                .map(productMapper::toDto);
    }

    @Transactional
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.toEntity(productDto);
        product.setStatus(AliveStatus.ACTIVE);
        Product saved = productRepository.save(product);
        return productMapper.toDto(saved);
    }

    public Optional<ProductDto> updateProduct(Integer id, ProductDto productDto) {
        return productRepository.findByIdAndCompany_Id(id,SecurityUtil.getCompanyClaim())
                .map(existing -> {
                    Product updatedProduct = productMapper.toEntity(productDto);
                    updatedProduct.setId(id);
                    updatedProduct.setCompany(existing.getCompany());
                    Product saved = productRepository.save(updatedProduct);
                    return productMapper.toDto(saved);
                });
    }

    public boolean deleteProduct(Integer id) {
        if (productRepository.existsById(Long.valueOf(id))) {
            productRepository.deleteById(Long.valueOf(id));
            return true;
        }
        return false;
    }
}
