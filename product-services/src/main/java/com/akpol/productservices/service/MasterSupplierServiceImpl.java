package com.akpol.productservices.service;

import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.SupplierDTO;
import com.akpol.productservices.mapper.MasterSupplierMapper;
import com.akpol.productservices.repository.MasterSupplierRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return masterSupplierRepository.findById(Long.parseLong(id)).map(masterSupplier -> masterSupplierMapper.mapEntityToDTO(masterSupplier)).orElse(null);
    }

    @Override
    public String saveOrUpdate(SupplierDTO supplierDTO) {
        MasterSupplier masterSupplier = masterSupplierMapper.mapDTOToEntity(supplierDTO);
        MasterSupplier resultSaveOrUpdate = masterSupplierRepository.save(masterSupplier);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<MasterSupplier> existingMasterSupplier = masterSupplierRepository.findById(Long.parseLong(id));
        if(existingMasterSupplier.isPresent()) {
            masterSupplierRepository.delete(existingMasterSupplier.get());
            return "success";
        } else {
            return "error";
        }
    }
}
