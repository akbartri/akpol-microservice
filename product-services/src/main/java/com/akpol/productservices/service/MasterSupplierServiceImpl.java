package com.akpol.productservices.service;

import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.SupplierDTO;
import com.akpol.productservices.mapper.MasterSupplierMapper;
import com.akpol.productservices.repository.MasterSupplierRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterSupplierServiceImpl implements MasterSupplierService {
    private MasterSupplierRepository masterSupplierRepository;
    private MasterSupplierMapper masterSupplierMapper;
    public MasterSupplierServiceImpl(
            MasterSupplierRepository masterSupplierRepository,
            MasterSupplierMapper masterSupplierMapper
    ) {
        this.masterSupplierRepository = masterSupplierRepository;
        this.masterSupplierMapper = masterSupplierMapper;
    }

    @Override
    public List<SupplierDTO> getAll() {
        return masterSupplierMapper.mapEntityListToDTOList(masterSupplierRepository.findAll());
    }

    @Override
    public SupplierDTO getById(String id) {
        return masterSupplierMapper.mapEntityToDTO(masterSupplierRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(SupplierDTO supplierDTO) {
        MasterSupplier masterSupplier = masterSupplierMapper.mapDTOToEntity(supplierDTO);
        MasterSupplier resultSaveOrUpdate = masterSupplierRepository.save(masterSupplier);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterSupplier existingMasterSupplier = masterSupplierRepository.getById(Long.parseLong(id));
        if(existingMasterSupplier != null) {
            masterSupplierRepository.delete(existingMasterSupplier);
            return "success";
        } else {
            return "error";
        }
    }
}
