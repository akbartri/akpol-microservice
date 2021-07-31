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
            paymentDTO.setId(dataPayment.getId() != null ? dataPayment.getId().toString() : null);
            paymentDTO.setPaymentType(dataPayment.getPaymentType());
            paymentDTO.setPaymentName(dataPayment.getPaymentName());
            paymentDTO.setStatus(dataPayment.getStatus());

            return paymentDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionPayment> mapDTOListToEntityList(List<PaymentDTO> paymentDTOList) {
        return paymentDTOList.stream().map(dataPaymentDTO -> {
            TransactionPayment payment = new TransactionPayment();
            payment.setId(dataPaymentDTO.getId() != null ? Long.parseLong(dataPaymentDTO.getId()) : null);
            payment.setPaymentName(dataPaymentDTO.getPaymentName());
            payment.setPaymentType(dataPaymentDTO.getPaymentType());
            payment.setStatus(dataPaymentDTO.getStatus());

            return payment;
        }).collect(Collectors.toList());
    }

    public PaymentDTO mapEntityToDTO(TransactionPayment transactionPayment) {
        if(transactionPayment != null) {
            PaymentDTO paymentDTO = new PaymentDTO();
            paymentDTO.setId(transactionPayment.getId() != null ? transactionPayment.getId().toString() : null);
            paymentDTO.setPaymentType(transactionPayment.getPaymentType());
            paymentDTO.setPaymentName(transactionPayment.getPaymentName());
            paymentDTO.setStatus(transactionPayment.getStatus());

            return paymentDTO;
        } else {
            return null;
        }
    }

    public TransactionPayment mapDTOToEntity(PaymentDTO paymentDTO) {
        if(paymentDTO != null) {
            TransactionPayment payment = new TransactionPayment();
            payment.setId(paymentDTO.getId() != null ? Long.parseLong(paymentDTO.getId()) : null);
            payment.setPaymentName(paymentDTO.getPaymentName());
            payment.setPaymentType(paymentDTO.getPaymentType());
            payment.setStatus(paymentDTO.getStatus());

            return payment;
        } else {
            return null;
        }
    }
}
