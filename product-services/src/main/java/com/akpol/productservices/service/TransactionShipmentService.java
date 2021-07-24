package com.akpol.productservices.service;

import com.akpol.commons.model.dto.ShipmentDTO;

import java.util.List;

public interface TransactionShipmentService {
    public List<ShipmentDTO> getAll();
    public ShipmentDTO getById(String id);
    public String saveOrUpdate(ShipmentDTO shipmentDTO);
    public String deleteById(String id);
}
