package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionOrderDetail;
import com.akpol.commons.model.dto.OrderDetailDTO;
import com.akpol.productservices.mapper.TransactionOrderDetailMapper;
import com.akpol.productservices.repository.TransactionOrderDetailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionOrderDetailServiceImpl implements TransactionOrderDetailService {
    private TransactionOrderDetailRepository transactionOrderDetailRepository;
    private TransactionOrderDetailMapper transactionOrderDetailMapper;
    public TransactionOrderDetailServiceImpl(
            TransactionOrderDetailRepository transactionOrderDetailRepository,
            TransactionOrderDetailMapper transactionOrderDetailMapper
    ) {
        this.transactionOrderDetailRepository = transactionOrderDetailRepository;
        this.transactionOrderDetailMapper = transactionOrderDetailMapper;
    }

    @Override
    public List<OrderDetailDTO> getAll() {
        return transactionOrderDetailMapper.mapEntityListToDTOList(transactionOrderDetailRepository.findAll());
    }

    @Override
    public OrderDetailDTO getById(String id) {
        return transactionOrderDetailMapper.mapEntityToDTO(transactionOrderDetailRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(OrderDetailDTO orderDetailDTO) {
        TransactionOrderDetail transactionOrderDetail = transactionOrderDetailMapper.mapDTOToEntity(orderDetailDTO);
        TransactionOrderDetail resultSaveOrUpdate = transactionOrderDetailRepository.save(transactionOrderDetail);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        TransactionOrderDetail existingTransactionOrderDetail = transactionOrderDetailRepository.getById(Long.parseLong(id));
        if(existingTransactionOrderDetail != null) {
            transactionOrderDetailRepository.delete(existingTransactionOrderDetail);
            return "success";
        } else {
            return "error";
        }
    }
}
