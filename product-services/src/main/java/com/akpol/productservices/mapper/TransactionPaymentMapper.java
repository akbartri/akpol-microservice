package com.akpol.productservices.mapper;

import com.akpol.commons.model.TransactionPayment;
import com.akpol.commons.model.dto.PaymentDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionPaymentMapper {

    public List<PaymentDTO> mapEntityListToDTOList(List<TransactionPayment> transactionPaymentList) {
        return transactionPaymentList.stream().map(dataPayment -> {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setId(dataPayment.getId().toString());
            paymentDTO.setPaymentType(dataPayment.getPaymentType());
            paymentDTO.setPaymentName(dataPayment.getPaymentName());
            paymentDTO.setStatus(dataPayment.getStatus());

            return paymentDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionPayment> mapDTOListToEntityList(List<PaymentDTO> paymentDTOList) {
        return paymentDTOList.stream().map(dataPaymentDTO -> {
            TransactionPayment payment = new TransactionPayment();
            payment.setId(Long.parseLong(dataPaymentDTO.getId()));
            payment.setPaymentName(dataPaymentDTO.getPaymentName());
            payment.setPaymentType(dataPaymentDTO.getPaymentType());
            payment.setStatus(dataPaymentDTO.getStatus());

            return payment;
        }).collect(Collectors.toList());
    }

    public PaymentDTO mapEntityToDTO(TransactionPayment transactionPayment) {
        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setId(transactionPayment.getId().toString());
        paymentDTO.setPaymentType(transactionPayment.getPaymentType());
        paymentDTO.setPaymentName(transactionPayment.getPaymentName());
        paymentDTO.setStatus(transactionPayment.getStatus());

        return paymentDTO;
    }

    public TransactionPayment mapDTOToEntity(PaymentDTO paymentDTO) {
        TransactionPayment payment = new TransactionPayment();
        payment.setId(Long.parseLong(paymentDTO.getId()));
        payment.setPaymentName(paymentDTO.getPaymentName());
        payment.setPaymentType(paymentDTO.getPaymentType());
        payment.setStatus(paymentDTO.getStatus());

        return payment;
    }
}
