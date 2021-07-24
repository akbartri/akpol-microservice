package com.akpol.productservices.service;

import com.akpol.commons.model.dto.PaymentDTO;

import java.util.List;

public interface TransactionPaymentService {
    public List<PaymentDTO> getAll();
    public PaymentDTO getById(String id);
    public String saveOrUpdate(PaymentDTO paymentDTO);
    public String deleteById(String id);
}
