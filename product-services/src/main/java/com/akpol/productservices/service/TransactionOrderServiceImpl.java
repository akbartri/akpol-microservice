package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionOrder;
import com.akpol.commons.model.dto.OrderDTO;
import com.akpol.productservices.mapper.TransactionOrderMapper;
import com.akpol.productservices.repository.TransactionOrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return transactionOrderRepository.findById(Long.parseLong(id)).map(transactionOrder -> transactionOrderMapper.mapEntityToDTO(transactionOrder)).orElse(null);
    }

    @Override
    public String saveOrUpdate(OrderDTO orderDTO) {
        TransactionOrder transactionOrder = transactionOrderMapper.mapDTOToEntity(orderDTO);
        TransactionOrder resultSaveOrUpdate = transactionOrderRepository.save(transactionOrder);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<TransactionOrder> existingTransactionOrder = transactionOrderRepository.findById(Long.parseLong(id));
        if(existingTransactionOrder.isPresent()) {
            transactionOrderRepository.delete(existingTransactionOrder.get());
            return "success";
        } else {
            return "error";
        }
    }
}
