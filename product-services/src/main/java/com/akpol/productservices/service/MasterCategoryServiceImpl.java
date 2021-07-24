package com.akpol.productservices.service;

import com.akpol.commons.model.MasterCategory;
import com.akpol.commons.model.dto.CategoryDTO;
import com.akpol.productservices.mapper.MasterCategoryMapper;
import com.akpol.productservices.repository.MasterCategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterCategoryServiceImpl implements MasterCategoryService {
    private MasterCategoryRepository masterCategoryRepository;
    private MasterCategoryMapper masterCategoryMapper;
    public MasterCategoryServiceImpl(
            MasterCategoryRepository masterCategoryRepository,
            MasterCategoryMapper masterCategoryMapper
    ) {
        this.masterCategoryRepository = masterCategoryRepository;
        this.masterCategoryMapper = masterCategoryMapper;
    }

    @Override
    public List<CategoryDTO> getAll() {
        return masterCategoryMapper.mapEntityListToDTOList(masterCategoryRepository.findAll());
    }

    @Override
    public CategoryDTO getById(String id) {
        return masterCategoryMapper.mapEntityToDTO(masterCategoryRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(CategoryDTO categoryDTO) {
        MasterCategory masterCategory = masterCategoryMapper.mapDTOToEntity(categoryDTO);
        MasterCategory resultSaveOrUpdate = masterCategoryRepository.save(masterCategory);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterCategory existingMasterCategory = masterCategoryRepository.getById(Long.parseLong(id));
        if(existingMasterCategory != null) {
            masterCategoryRepository.delete(existingMasterCategory);
            return "success";
        } else {
            return "error";
        }
    }
}
