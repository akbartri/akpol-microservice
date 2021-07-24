package com.akpol.productservices.service;

import com.akpol.commons.model.dto.SupplierDTO;

import java.util.List;

public interface MasterSupplierService {
    public List<SupplierDTO> getAll();
    public SupplierDTO getById(String id);
    public String saveOrUpdate(SupplierDTO supplierDTO);
    public String deleteById(String id);
}
