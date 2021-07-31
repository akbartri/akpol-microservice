package com.akpol.productservices.service;

import com.akpol.commons.model.MasterProduct;
import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.productservices.mapper.MasterProductMapper;
import com.akpol.productservices.repository.MasterProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return masterProductRepository.findById(Long.parseLong(id)).map(masterProduct -> masterProductMapper.mapEntityToDTO(masterProduct)).orElse(null);
    }

    @Override
    public String saveOrUpdate(ProductDTO productDTO) {
        MasterProduct masterProduct = masterProductMapper.mapDTOToEntity(productDTO);
        MasterProduct resultSaveOrUpdate = masterProductRepository.save(masterProduct);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<MasterProduct> existingMasterProduct = masterProductRepository.findById(Long.parseLong(id));
        if(existingMasterProduct.isPresent()) {
            masterProductRepository.delete(existingMasterProduct.get());
            return "success";
        } else {
            return "error";
        }
    }
}
