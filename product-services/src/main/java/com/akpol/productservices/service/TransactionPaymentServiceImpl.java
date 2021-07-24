package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionPayment;
import com.akpol.commons.model.dto.PaymentDTO;
import com.akpol.productservices.mapper.TransactionPaymentMapper;
import com.akpol.productservices.repository.TransactionPaymentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TransactionPaymentServiceImpl implements TransactionPaymentService {
    private TransactionPaymentRepository transactionPaymentRepository;
    private TransactionPaymentMapper transactionPaymentMapper;
    public TransactionPaymentServiceImpl(
            TransactionPaymentRepository transactionPaymentRepository,
            TransactionPaymentMapper transactionPaymentMapper
    ) {
        this.transactionPaymentRepository = transactionPaymentRepository;
        this.transactionPaymentMapper = transactionPaymentMapper;
    }

    @Override
    public List<PaymentDTO> getAll() {
        return transactionPaymentMapper.mapEntityListToDTOList(transactionPaymentRepository.findAll());
    }

    @Override
    public PaymentDTO getById(String id) {
        return transactionPaymentMapper.mapEntityToDTO(transactionPaymentRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(PaymentDTO paymentDTO) {
        TransactionPayment transactionPayment = transactionPaymentMapper.mapDTOToEntity(paymentDTO);
        TransactionPayment resultSaveOrUpdate = transactionPaymentRepository.save(transactionPayment);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        TransactionPayment existingTransactionPayment = transactionPaymentRepository.getById(Long.parseLong(id));
        if(existingTransactionPayment != null) {
            transactionPaymentRepository.delete(existingTransactionPayment);
            return "success";
        } else {
            return "error";
        }
    }
}
