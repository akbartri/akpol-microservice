package com.akpol.productservices.service;

import com.akpol.commons.model.MasterProductDetail;
import com.akpol.commons.model.dto.ProductDetailDTO;
import com.akpol.productservices.mapper.MasterProductDetailMapper;
import com.akpol.productservices.repository.MasterProductDetailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterProductDetailServiceImpl implements MasterProductDetailService {
    private MasterProductDetailRepository masterProductDetailRepository;
    private MasterProductDetailMapper masterProductDetailMapper;
    public MasterProductDetailServiceImpl(
            MasterProductDetailRepository masterProductDetailRepository,
            MasterProductDetailMapper masterProductDetailMapper
    ) {
        this.masterProductDetailRepository = masterProductDetailRepository;
        this.masterProductDetailMapper = masterProductDetailMapper;
    }

    @Override
    public List<ProductDetailDTO> getAll() {
        return masterProductDetailMapper.mapEntityListToDTOList(masterProductDetailRepository.findAll());
    }

    @Override
    public ProductDetailDTO getById(String id) {
        return masterProductDetailMapper.mapEntityToDTO(masterProductDetailRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(ProductDetailDTO productDetailDTO) {
        MasterProductDetail masterProductDetail = masterProductDetailMapper.mapDTOToEntity(productDetailDTO);
        MasterProductDetail resultSaveOrUpdate = masterProductDetailRepository.save(masterProductDetail);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterProductDetail existingMasterProductDetail = masterProductDetailRepository.getById(Long.parseLong(id));
        if(existingMasterProductDetail != null) {
            masterProductDetailRepository.delete(existingMasterProductDetail);
            return "success";
        } else {
            return "error";
        }
    }
}
