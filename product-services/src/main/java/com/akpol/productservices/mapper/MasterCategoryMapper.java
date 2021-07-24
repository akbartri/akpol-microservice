package com.akpol.productservices.mapper;

import com.akpol.commons.model.MasterCategory;
import com.akpol.commons.model.dto.CategoryDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterCategoryMapper {

    public List<CategoryDTO> mapEntityListToDTOList(List<MasterCategory> masterCategoryList) {
        return masterCategoryList.stream().map(dataCategory -> {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(dataCategory.getId().toString());
            categoryDTO.setName(dataCategory.getName());
            categoryDTO.setDescription(dataCategory.getDescription());
            categoryDTO.setParent(dataCategory.getParent().toString());
            categoryDTO.setActive(dataCategory.isActive() ? "true" : "false");

            return categoryDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterCategory> mapDTOListToEntityList(List<CategoryDTO> categoryDTOList) {
        return categoryDTOList.stream().map(dataCategoryDTO -> {
            MasterCategory category = new MasterCategory();
            category.setId(Long.parseLong(dataCategoryDTO.getId()));
            category.setName(dataCategoryDTO.getName());
            category.setDescription(dataCategoryDTO.getDescription());
            category.setParent(Long.parseLong(dataCategoryDTO.getParent()));
            category.setActive(dataCategoryDTO.getActive().equalsIgnoreCase("true"));

            return category;
        }).collect(Collectors.toList());
    }

    public CategoryDTO mapEntityToDTO(MasterCategory masterCategory) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(masterCategory.getId().toString());
        categoryDTO.setName(masterCategory.getName());
        categoryDTO.setDescription(masterCategory.getDescription());
        categoryDTO.setParent(masterCategory.getParent().toString());
        categoryDTO.setActive(masterCategory.isActive() ? "true" : "false");

        return categoryDTO;
    }

    public MasterCategory mapDTOToEntity(CategoryDTO categoryDTO) {
        MasterCategory category = new MasterCategory();
        category.setId(Long.parseLong(categoryDTO.getId()));
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setParent(Long.parseLong(categoryDTO.getParent()));
        category.setActive(categoryDTO.getActive().equalsIgnoreCase("true"));

        return category;
    }
}
