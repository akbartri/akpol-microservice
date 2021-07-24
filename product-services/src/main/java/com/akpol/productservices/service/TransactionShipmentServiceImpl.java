package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionShipment;
import com.akpol.commons.model.dto.ShipmentDTO;
import com.akpol.productservices.mapper.TransactionShipmentMapper;
import com.akpol.productservices.repository.TransactionShipmentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionShipmentServiceImpl implements TransactionShipmentService {
    private TransactionShipmentRepository transactionShipmentRepository;
    private TransactionShipmentMapper transactionShipmentMapper;
    public TransactionShipmentServiceImpl(
            TransactionShipmentRepository transactionShipmentRepository,
            TransactionShipmentMapper transactionShipmentMapper
    ) {
        this.transactionShipmentRepository = transactionShipmentRepository;
        this.transactionShipmentMapper = transactionShipmentMapper;
    }

    @Override
    public List<ShipmentDTO> getAll() {
        return transactionShipmentMapper.mapEntityListToDTOList(transactionShipmentRepository.findAll());
    }

    @Override
    public ShipmentDTO getById(String id) {
        return transactionShipmentMapper.mapEntityToDTO(transactionShipmentRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(ShipmentDTO shipmentDTO) {
        TransactionShipment transactionShipment = transactionShipmentMapper.mapDTOToEntity(shipmentDTO);
        TransactionShipment resultSaveOrUpdate = transactionShipmentRepository.save(transactionShipment);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        TransactionShipment existingTransactionShipment = transactionShipmentRepository.getById(Long.parseLong(id));
        if(existingTransactionShipment != null) {
            transactionShipmentRepository.delete(existingTransactionShipment);
            return "success";
        } else {
            return "error";
        }
    }
}
