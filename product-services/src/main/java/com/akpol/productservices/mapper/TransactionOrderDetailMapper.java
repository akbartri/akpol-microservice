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
            orderDetailDTO.setId(dataOrderDetail.getId() != null ? dataOrderDetail.getId().toString() : null);

            if(dataOrderDetail.getOrderId() != null) {
                TransactionOrder order = transactionOrderRepository.getById(dataOrderDetail.getOrderId());
                if(order != null) {
                    orderDetailDTO.setOrderNo(order.getOrderNo());
                }
            }

            orderDetailDTO.setPrice(dataOrderDetail.getPrice() != null ? dataOrderDetail.getPrice().toString() : null);
            orderDetailDTO.setQuantity(dataOrderDetail.getQuantity() != null ? dataOrderDetail.getQuantity().toString() : null);
            orderDetailDTO.setTotalPrice(dataOrderDetail.getTotalPrice() != null ? dataOrderDetail.getTotalPrice().toString() : null);

            return orderDetailDTO;
        }).collect(Collectors.toList());
    }

    public List<TransactionOrderDetail> mapDTOListToEntityList(List<OrderDetailDTO> orderDetailDTOList) {
        return orderDetailDTOList.stream().map(dataOrderDetailDTO -> {
            TransactionOrderDetail orderDetail = new TransactionOrderDetail();
            orderDetail.setId(dataOrderDetailDTO.getId() != null ? Long.parseLong(dataOrderDetailDTO.getId()) : null);

            if(dataOrderDetailDTO.getOrderNo() != null) {
                TransactionOrder transactionOrder = transactionOrderRepository.findByOrderNoEquals(dataOrderDetailDTO.getOrderNo());
                if(transactionOrder != null) {
                    orderDetail.setOrderId(transactionOrder.getId());
                }
            }

            orderDetail.setPrice(dataOrderDetailDTO.getPrice() != null ? Long.parseLong(dataOrderDetailDTO.getPrice()) : null);
            orderDetail.setQuantity(dataOrderDetailDTO.getQuantity() != null ? Long.parseLong(dataOrderDetailDTO.getQuantity()) : null);
            orderDetail.setTotalPrice(dataOrderDetailDTO.getTotalPrice() != null ? Long.parseLong(dataOrderDetailDTO.getTotalPrice()) : null);

            return orderDetail;
        }).collect(Collectors.toList());
    }

    public OrderDetailDTO mapEntityToDTO(TransactionOrderDetail transactionOrderDetail) {
        if(transactionOrderDetail != null) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
            orderDetailDTO.setId(transactionOrderDetail.getId() != null ? transactionOrderDetail.getId().toString() : null);

            if(transactionOrderDetail.getOrderId() != null) {
                TransactionOrder order = transactionOrderRepository.getById(transactionOrderDetail.getOrderId());
                if(order != null) {
                    orderDetailDTO.setOrderNo(order.getOrderNo());
                }
            }

            orderDetailDTO.setPrice(transactionOrderDetail.getPrice() != null ? transactionOrderDetail.getPrice().toString() : null);
            orderDetailDTO.setQuantity(transactionOrderDetail.getQuantity() != null ? transactionOrderDetail.getQuantity().toString() : null);
            orderDetailDTO.setTotalPrice(transactionOrderDetail.getTotalPrice() != null ? transactionOrderDetail.getTotalPrice().toString() : null);

            return orderDetailDTO;
        } else {
            return null;
        }
    }

    public TransactionOrderDetail mapDTOToEntity(OrderDetailDTO orderDetailDTO) {
        if(orderDetailDTO != null) {
            TransactionOrderDetail orderDetail = new TransactionOrderDetail();
            orderDetail.setId(orderDetailDTO.getId() != null ? Long.parseLong(orderDetailDTO.getId()) : null);

            if(orderDetailDTO.getOrderNo() != null) {
                TransactionOrder transactionOrder = transactionOrderRepository.findByOrderNoEquals(orderDetailDTO.getOrderNo());
                if(transactionOrder != null) {
                    orderDetail.setOrderId(transactionOrder.getId());
                }
            }

            orderDetail.setPrice(orderDetailDTO.getPrice() != null ? Long.parseLong(orderDetailDTO.getPrice()) : null);
            orderDetail.setQuantity(orderDetailDTO.getQuantity() != null ? Long.parseLong(orderDetailDTO.getQuantity()) : null);
            orderDetail.setTotalPrice(orderDetailDTO.getTotalPrice() != null ? Long.parseLong(orderDetailDTO.getTotalPrice()) : null);

            return orderDetail;
        } else {
            return null;
        }
    }
}
