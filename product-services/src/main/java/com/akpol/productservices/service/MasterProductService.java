package com.akpol.productservices.service;

import com.akpol.commons.model.dto.ProductDTO;

import java.util.List;

public interface MasterProductService {
    public List<ProductDTO> getAll();
    public ProductDTO getById(String id);
    public String saveOrUpdate(ProductDTO productDTO);
    public String deleteById(String id);
}
