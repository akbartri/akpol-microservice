package com.akpol.productservices.mapper;

import com.akpol.commons.model.*;
import com.akpol.commons.model.dto.MemberDTO;
import com.akpol.commons.model.dto.OrderDTO;
import com.akpol.commons.model.dto.ProductDTO;
import com.akpol.productservices.repository.MasterCategoryRepository;
import com.akpol.productservices.repository.MasterSupplierRepository;
import com.akpol.productservices.repository.TransactionPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionOrderMapper {

    @Autowired
    private TransactionPaymentRepository transactionPaymentRepository;

    @Autowired
    private RestTemplate restTemplate;

    public List<OrderDTO> mapEntityListToDTOList(List<TransactionOrder> transactionOrderList) {
        return transactionOrderList.stream().map(dataOrder -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(dataOrder.getId().toString());
            orderDTO.setOrderNo(dataOrder.getOrderNo());
            orderDTO.setStatus(dataOrder.getStatus());
            orderDTO.setTimestamp(dataOrder.getTimestamp().toString());

            if(dataOrder.getMemberId() != null) {
                MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/" + dataOrder.getMemberId(), MemberDTO.class);
                if(memberDTO != null) {
                    orderDTO.setMemberName(memberDTO.getFirstName() + " " + memberDTO.getLastName());
                }
            }

            if(dataOrder.getPaymentId() != null) {
                TransactionPayment payment = transactionPaymentRepository.getById(dataOrder.getPaymentId());
                if(payment != null) {
                    orderDTO.setPaymentName(payment.getPaymentName());
                }
            }

            return orderDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionOrder> mapDTOListToEntityList(List<OrderDTO> orderDTOList) {
        return orderDTOList.stream().map(dataOrderDTO -> {
            TransactionOrder order = new TransactionOrder();
            order.setId(Long.parseLong(dataOrderDTO.getId()));
            order.setOrderNo(dataOrderDTO.getOrderNo());
            order.setStatus(dataOrderDTO.getStatus());
            order.setTimestamp(Timestamp.valueOf(dataOrderDTO.getTimestamp()));

            if(dataOrderDTO.getMemberName() != null) {
                MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/name/" + dataOrderDTO.getMemberName(), MemberDTO.class);
                if(memberDTO != null) {
                    order.setMemberId(Long.parseLong(memberDTO.getId()));
                }
            }

            order.setMemberId(Long.parseLong(dataOrderDTO.getMemberName()));

            if(dataOrderDTO.getPaymentName() != null) {
                TransactionPayment payment = transactionPaymentRepository.findByPaymentNameEquals(dataOrderDTO.getPaymentName());
                if(payment != null) {
                    order.setPaymentId(payment.getId());
                }
            }

            return order;
        }).collect(Collectors.toList());
    }

    public OrderDTO mapEntityToDTO(TransactionOrder transactionOrder) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(transactionOrder.getId().toString());
        orderDTO.setOrderNo(transactionOrder.getOrderNo());
        orderDTO.setStatus(transactionOrder.getStatus());
        orderDTO.setTimestamp(transactionOrder.getTimestamp().toString());

        if(transactionOrder.getMemberId() != null) {
            MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/" + transactionOrder.getMemberId(), MemberDTO.class);
            if(memberDTO != null) {
                orderDTO.setMemberName(memberDTO.getFirstName() + " " + memberDTO.getLastName());
            }
        }

        if(transactionOrder.getPaymentId() != null) {
            TransactionPayment payment = transactionPaymentRepository.getById(transactionOrder.getPaymentId());
            if(payment != null) {
                orderDTO.setPaymentName(payment.getPaymentName());
            }
        }

        return orderDTO;
    }

    public TransactionOrder mapDTOToEntity(OrderDTO orderDTO) {
        TransactionOrder order = new TransactionOrder();
        order.setId(Long.parseLong(orderDTO.getId()));
        order.setOrderNo(orderDTO.getOrderNo());
        order.setStatus(orderDTO.getStatus());
        order.setTimestamp(Timestamp.valueOf(orderDTO.getTimestamp()));

        if(orderDTO.getMemberName() != null) {
            MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/name/" + orderDTO.getMemberName(), MemberDTO.class);
            if(memberDTO != null) {
                order.setMemberId(Long.parseLong(memberDTO.getId()));
            }
        }

        if(orderDTO.getPaymentName() != null) {
            TransactionPayment payment = transactionPaymentRepository.findByPaymentNameEquals(orderDTO.getPaymentName());
            if(payment != null) {
                order.setPaymentId(payment.getId());
            }
        }

        return order;
    }
}
