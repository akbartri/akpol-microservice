package com.akpol.productservices.service;

import com.akpol.commons.model.MasterCategory;
import com.akpol.commons.model.dto.CategoryDTO;
import com.akpol.productservices.mapper.MasterCategoryMapper;
import com.akpol.productservices.repository.MasterCategoryRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return masterCategoryRepository.findById(Long.parseLong(id)).map(masterCategory -> masterCategoryMapper.mapEntityToDTO(masterCategory)).orElse(null);
    }

    @Override
    public String saveOrUpdate(CategoryDTO categoryDTO) {
        MasterCategory masterCategory = masterCategoryMapper.mapDTOToEntity(categoryDTO);
        MasterCategory resultSaveOrUpdate = masterCategoryRepository.save(masterCategory);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<MasterCategory> existingMasterCategory = masterCategoryRepository.findById(Long.parseLong(id));
        if(existingMasterCategory.isPresent()) {
            masterCategoryRepository.delete(existingMasterCategory.get());
            return "success";
        } else {
            return "error";
        }
    }
}
