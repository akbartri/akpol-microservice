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
            categoryDTO.setId(dataCategory.getId() != null ? dataCategory.getId().toString() : null);
            categoryDTO.setName(dataCategory.getName());
            categoryDTO.setDescription(dataCategory.getDescription());
            categoryDTO.setParent(dataCategory.getParent().toString());
            categoryDTO.setActive(dataCategory.isActive() != null ? dataCategory.isActive() ? "true" : "false" : null);

            return categoryDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterCategory> mapDTOListToEntityList(List<CategoryDTO> categoryDTOList) {
        return categoryDTOList.stream().map(dataCategoryDTO -> {
            MasterCategory category = new MasterCategory();
            category.setId(dataCategoryDTO.getId() != null ? Long.parseLong(dataCategoryDTO.getId()) : null);
            category.setName(dataCategoryDTO.getName());
            category.setDescription(dataCategoryDTO.getDescription());
            category.setParent(Long.parseLong(dataCategoryDTO.getParent()));
            category.setActive(dataCategoryDTO.getActive() != null ? dataCategoryDTO.getActive().equalsIgnoreCase("true") : null);

            return category;
        }).collect(Collectors.toList());
    }

    public CategoryDTO mapEntityToDTO(MasterCategory masterCategory) {
        if(masterCategory != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(masterCategory.getId() != null ? masterCategory.getId().toString() : null);
            categoryDTO.setName(masterCategory.getName());
            categoryDTO.setDescription(masterCategory.getDescription());
            categoryDTO.setParent(masterCategory.getParent().toString());
            categoryDTO.setActive(masterCategory.isActive() != null ? masterCategory.isActive() ? "true" : "false" : null);

            return categoryDTO;
        } else {
            return null;
        }
    }

    public MasterCategory mapDTOToEntity(CategoryDTO categoryDTO) {
        if(categoryDTO != null) {
            MasterCategory category = new MasterCategory();
            category.setId(categoryDTO.getId() != null ? Long.parseLong(categoryDTO.getId()) : null);
            category.setName(categoryDTO.getName());
            category.setDescription(categoryDTO.getDescription());
            category.setParent(Long.parseLong(categoryDTO.getParent()));
            category.setActive(categoryDTO.getActive() != null ? categoryDTO.getActive().equalsIgnoreCase("true") : null);

            return category;
        } else {
            return null;
        }
    }
}
