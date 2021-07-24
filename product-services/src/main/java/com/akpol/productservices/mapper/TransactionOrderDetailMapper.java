package com.akpol.productservices.mapper;

import com.akpol.commons.model.*;
import com.akpol.commons.model.dto.OrderDetailDTO;
import com.akpol.productservices.repository.TransactionOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionOrderDetailMapper {

    @Autowired
    private TransactionOrderRepository transactionOrderRepository;

    public List<OrderDetailDTO> mapEntityListToDTOList(List<TransactionOrderDetail> transactionOrderDetailList) {
        return transactionOrderDetailList.stream().map(dataOrderDetail -> {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setId(dataOrderDetail.getId().toString());

            if(dataOrderDetail.getTransactionOrderId() != null) {
                TransactionOrder order = transactionOrderRepository.getById(dataOrderDetail.getTransactionOrderId());
                if(order != null) {
                    orderDetailDTO.setOrderNo(order.getOrderNo());
                }
            }

            orderDetailDTO.setPrice(dataOrderDetail.getPrice().toString());
            orderDetailDTO.setQuantity(dataOrderDetail.getQuantity().toString());
            orderDetailDTO.setTotalPrice(dataOrderDetail.getTotalPrice().toString());

            return orderDetailDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionOrderDetail> mapDTOListToEntityList(List<OrderDetailDTO> orderDetailDTOList) {
        return orderDetailDTOList.stream().map(dataOrderDetailDTO -> {
            TransactionOrderDetail orderDetail = new TransactionOrderDetail();
            orderDetail.setId(Long.parseLong(dataOrderDetailDTO.getId()));

            if(dataOrderDetailDTO.getOrderNo() != null) {
                TransactionOrder transactionOrder = transactionOrderRepository.findByOrderNoEquals(dataOrderDetailDTO.getOrderNo());
                if(transactionOrder != null) {
                    orderDetail.setTransactionOrderId(transactionOrder.getId());
                }
            }

            orderDetail.setPrice(Long.parseLong(dataOrderDetailDTO.getPrice()));
            orderDetail.setQuantity(Long.parseLong(dataOrderDetailDTO.getQuantity()));
            orderDetail.setTotalPrice(Long.parseLong(dataOrderDetailDTO.getTotalPrice()));

            return orderDetail;
        }).collect(Collectors.toList());
    }

    public OrderDetailDTO mapEntityToDTO(TransactionOrderDetail transactionOrderDetail) {
        OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
        orderDetailDTO.setId(transactionOrderDetail.getId().toString());

        if(transactionOrderDetail.getTransactionOrderId() != null) {
            TransactionOrder order = transactionOrderRepository.getById(transactionOrderDetail.getTransactionOrderId());
            if(order != null) {
                orderDetailDTO.setOrderNo(order.getOrderNo());
            }
        }

        orderDetailDTO.setPrice(transactionOrderDetail.getPrice().toString());
        orderDetailDTO.setQuantity(transactionOrderDetail.getQuantity().toString());
        orderDetailDTO.setTotalPrice(transactionOrderDetail.getTotalPrice().toString());

        return orderDetailDTO;
    }

    public TransactionOrderDetail mapDTOToEntity(OrderDetailDTO orderDetailDTO) {
        TransactionOrderDetail orderDetail = new TransactionOrderDetail();
        orderDetail.setId(Long.parseLong(orderDetailDTO.getId()));

        if(orderDetailDTO.getOrderNo() != null) {
            TransactionOrder transactionOrder = transactionOrderRepository.findByOrderNoEquals(orderDetailDTO.getOrderNo());
            if(transactionOrder != null) {
                orderDetail.setTransactionOrderId(transactionOrder.getId());
            }
        }

        orderDetail.setPrice(Long.parseLong(orderDetailDTO.getPrice()));
        orderDetail.setQuantity(Long.parseLong(orderDetailDTO.getQuantity()));
        orderDetail.setTotalPrice(Long.parseLong(orderDetailDTO.getTotalPrice()));

        return orderDetail;
    }
}
