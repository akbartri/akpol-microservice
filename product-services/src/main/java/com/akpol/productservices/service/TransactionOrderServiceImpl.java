package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionOrder;
import com.akpol.commons.model.dto.OrderDTO;
import com.akpol.productservices.mapper.TransactionOrderMapper;
import com.akpol.productservices.repository.TransactionOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionOrderServiceImpl implements TransactionOrderService {
    private TransactionOrderRepository transactionOrderRepository;
    private TransactionOrderMapper transactionOrderMapper;
    public TransactionOrderServiceImpl(
            TransactionOrderRepository transactionOrderRepository,
            TransactionOrderMapper transactionOrderMapper
    ) {
        this.transactionOrderRepository = transactionOrderRepository;
        this.transactionOrderMapper = transactionOrderMapper;
    }

    @Override
    public List<OrderDTO> getAll() {
        return transactionOrderMapper.mapEntityListToDTOList(transactionOrderRepository.findAll());
    }

    @Override
    public OrderDTO getById(String id) {
        return transactionOrderMapper.mapEntityToDTO(transactionOrderRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(OrderDTO orderDTO) {
        TransactionOrder transactionOrder = transactionOrderMapper.mapDTOToEntity(orderDTO);
        TransactionOrder resultSaveOrUpdate = transactionOrderRepository.save(transactionOrder);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        TransactionOrder existingTransactionOrder = transactionOrderRepository.getById(Long.parseLong(id));
        if(existingTransactionOrder != null) {
            transactionOrderRepository.delete(existingTransactionOrder);
            return "success";
        } else {
            return "error";
        }
    }
}
