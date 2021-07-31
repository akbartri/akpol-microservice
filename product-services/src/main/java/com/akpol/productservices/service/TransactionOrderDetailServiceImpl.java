package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionOrderDetail;
import com.akpol.commons.model.dto.OrderDetailDTO;
import com.akpol.productservices.mapper.TransactionOrderDetailMapper;
import com.akpol.productservices.repository.TransactionOrderDetailRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return transactionOrderDetailRepository.findById(Long.parseLong(id)).map(transactionOrderDetail -> transactionOrderDetailMapper.mapEntityToDTO(transactionOrderDetail)).orElse(null);
    }

    @Override
    public String saveOrUpdate(OrderDetailDTO orderDetailDTO) {
        TransactionOrderDetail transactionOrderDetail = transactionOrderDetailMapper.mapDTOToEntity(orderDetailDTO);
        TransactionOrderDetail resultSaveOrUpdate = transactionOrderDetailRepository.save(transactionOrderDetail);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<TransactionOrderDetail> existingTransactionOrderDetail = transactionOrderDetailRepository.findById(Long.parseLong(id));
        if(existingTransactionOrderDetail.isPresent()) {
            transactionOrderDetailRepository.delete(existingTransactionOrderDetail.get());
            return "success";
        } else {
            return "error";
        }
    }
}
