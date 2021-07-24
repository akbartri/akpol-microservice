package com.akpol.productservices.service;

import com.akpol.commons.model.MasterProduct;
import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.productservices.mapper.MasterProductMapper;
import com.akpol.productservices.repository.MasterProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterProductServiceImpl implements MasterProductService {
    private MasterProductRepository masterProductRepository;
    private MasterProductMapper masterProductMapper;
    public MasterProductServiceImpl(
            MasterProductRepository masterProductRepository,
            MasterProductMapper masterProductMapper
    ) {
        this.masterProductRepository = masterProductRepository;
        this.masterProductMapper = masterProductMapper;
    }

    @Override
    public List<ProductDTO> getAll() {
        return masterProductMapper.mapEntityListToDTOList(masterProductRepository.findAll());
    }

    @Override
    public ProductDTO getById(String id) {
        return masterProductMapper.mapEntityToDTO(masterProductRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(ProductDTO productDTO) {
        MasterProduct masterProduct = masterProductMapper.mapDTOToEntity(productDTO);
        MasterProduct resultSaveOrUpdate = masterProductRepository.save(masterProduct);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterProduct existingMasterProduct = masterProductRepository.getById(Long.parseLong(id));
        if(existingMasterProduct != null) {
            masterProductRepository.delete(existingMasterProduct);
            return "success";
        } else {
            return "error";
        }
    }
}
