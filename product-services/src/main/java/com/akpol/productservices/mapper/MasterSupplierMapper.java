package com.akpol.productservices.mapper;

import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.SupplierDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterSupplierMapper {

    public List<SupplierDTO> mapEntityListToDTOList(List<MasterSupplier> masterSupplierList) {
        return masterSupplierList.stream().map(dataSupplier -> {
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setId(dataSupplier.getId() != null ? dataSupplier.getId().toString() : null);
            supplierDTO.setName(dataSupplier.getName());
            supplierDTO.setAddress(dataSupplier.getAddress());
            supplierDTO.setPhone(dataSupplier.getPhone());
            supplierDTO.setSupplierLink(dataSupplier.getSupplierLink());

            return supplierDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterSupplier> mapDTOListToEntityList(List<SupplierDTO> supplierDTOList) {
        return supplierDTOList.stream().map(dataSupplierDTO -> {
            MasterSupplier supplier = new MasterSupplier();
            supplier.setId(dataSupplierDTO.getId() != null ? Long.parseLong(dataSupplierDTO.getId()) : null);
            supplier.setName(dataSupplierDTO.getName());
            supplier.setAddress(dataSupplierDTO.getAddress());
            supplier.setPhone(dataSupplierDTO.getPhone());
            supplier.setSupplierLink(dataSupplierDTO.getSupplierLink());

            return supplier;
        }).collect(Collectors.toList());
    }

    public SupplierDTO mapEntityToDTO(MasterSupplier masterSupplier) {
        if(masterSupplier != null) {
            SupplierDTO supplierDTO = new SupplierDTO();
            supplierDTO.setId(masterSupplier.getId() != null ? masterSupplier.getId().toString() : null);
            supplierDTO.setName(masterSupplier.getName());
            supplierDTO.setAddress(masterSupplier.getAddress());
            supplierDTO.setPhone(masterSupplier.getPhone());
            supplierDTO.setSupplierLink(masterSupplier.getSupplierLink());

            return supplierDTO;
        } else {
            return null;
        }
    }

    public MasterSupplier mapDTOToEntity(SupplierDTO supplierDTO) {
        if(supplierDTO != null) {
            MasterSupplier supplier = new MasterSupplier();
            supplier.setId(supplierDTO.getId() != null ? Long.parseLong(supplierDTO.getId()) : null);
            supplier.setName(supplierDTO.getName());
            supplier.setAddress(supplierDTO.getAddress());
            supplier.setPhone(supplierDTO.getPhone());
            supplier.setSupplierLink(supplierDTO.getSupplierLink());

            return supplier;
        } else {
            return null;
        }
    }
}
