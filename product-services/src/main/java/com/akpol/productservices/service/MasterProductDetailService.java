package com.akpol.productservices.service;

import com.akpol.commons.model.dto.ProductDetailDTO;

import java.util.List;

public interface MasterProductDetailService {
    public List<ProductDetailDTO> getAll();
    public ProductDetailDTO getById(String id);
    public String saveOrUpdate(ProductDetailDTO productDetailDTO);
    public String deleteById(String id);
}
