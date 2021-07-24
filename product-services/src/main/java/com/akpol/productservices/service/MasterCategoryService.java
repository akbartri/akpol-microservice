package com.akpol.productservices.service;

import com.akpol.commons.model.dto.CategoryDTO;

import java.util.List;

public interface MasterCategoryService {
    public List<CategoryDTO> getAll();
    public CategoryDTO getById(String id);
    public String saveOrUpdate(CategoryDTO categoryDTO);
    public String deleteById(String id);
}
