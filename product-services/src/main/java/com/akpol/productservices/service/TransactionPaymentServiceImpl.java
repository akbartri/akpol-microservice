package com.akpol.productservices.service;

import com.akpol.commons.model.TransactionPayment;
import com.akpol.commons.model.dto.PaymentDTO;
import com.akpol.productservices.mapper.TransactionPaymentMapper;
import com.akpol.productservices.repository.TransactionPaymentRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return transactionPaymentRepository.findById(Long.parseLong(id)).map(transactionPayment -> transactionPaymentMapper.mapEntityToDTO(transactionPayment)).orElse(null);
    }

    @Override
    public String saveOrUpdate(PaymentDTO paymentDTO) {
        TransactionPayment transactionPayment = transactionPaymentMapper.mapDTOToEntity(paymentDTO);
        TransactionPayment resultSaveOrUpdate = transactionPaymentRepository.save(transactionPayment);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<TransactionPayment> existingTransactionPayment = transactionPaymentRepository.findById(Long.parseLong(id));
        if(existingTransactionPayment.isPresent()) {
            transactionPaymentRepository.delete(existingTransactionPayment.get());
            return "success";
        } else {
            return "error";
        }
    }
}
