package com.akpol.productservices.mapper;

import com.akpol.commons.model.*;
import com.akpol.commons.model.dto.MemberDTO;
import com.akpol.commons.model.dto.OrderDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.repository.TransactionPaymentRepository;
import com.akpol.productservices.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionOrderMapper {

    @Autowired
    private TransactionPaymentRepository transactionPaymentRepository;

    @Autowired
    private ServiceUtil serviceUtil;

    @Value("${akpol.account-base-url}")
    private String accountBaseUrl;

    @Value("${akpol.api.member}")
    private String apiMember;

    @Value("${akpol.api.member-name}")
    private String apiMemberName;

    public List<OrderDTO> mapEntityListToDTOList(List<TransactionOrder> transactionOrderList) {
        return transactionOrderList.stream().map(dataOrder -> {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(dataOrder.getId() != null ? dataOrder.getId().toString() : null);
            orderDTO.setOrderNo(dataOrder.getOrderNo());
            orderDTO.setStatus(dataOrder.getStatus());
            orderDTO.setTimestamp(dataOrder.getTimestamp() != null ? dataOrder.getTimestamp().toString() : null);

            if(dataOrder.getMemberId() != null) {
                String url = accountBaseUrl+apiMember+dataOrder.getMemberId();
//                MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/" + dataOrder.getMemberId(), MemberDTO.class);
                ResponseDTO responseDTO = serviceUtil.getDTO(url);
                if(responseDTO != null && responseDTO.getContents() != null) {
                    MemberDTO memberDTO = (MemberDTO) responseDTO.getContents();
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
            order.setId(dataOrderDTO.getId() != null ? Long.parseLong(dataOrderDTO.getId()) : null);
            order.setOrderNo(dataOrderDTO.getOrderNo());
            order.setStatus(dataOrderDTO.getStatus());
            order.setTimestamp(dataOrderDTO.getTimestamp() != null ? Timestamp.valueOf(dataOrderDTO.getTimestamp()) : null);

            if(dataOrderDTO.getMemberName() != null) {
                String url = accountBaseUrl+apiMemberName+dataOrderDTO.getMemberName();
//                MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/name/" + dataOrderDTO.getMemberName(), MemberDTO.class);
                ResponseDTO responseDTO = serviceUtil.getDTO(url);
                if(responseDTO != null && responseDTO.getContents() != null) {
                    MemberDTO memberDTO = (MemberDTO) responseDTO.getContents();
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
        if(transactionOrder != null) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(transactionOrder.getId() != null ? transactionOrder.getId().toString() : null);
            orderDTO.setOrderNo(transactionOrder.getOrderNo());
            orderDTO.setStatus(transactionOrder.getStatus());
            orderDTO.setTimestamp(transactionOrder.getTimestamp() != null ? transactionOrder.getTimestamp().toString() : null);

            if(transactionOrder.getMemberId() != null) {
                String url = accountBaseUrl+apiMember+transactionOrder.getMemberId();
//            MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/" + transactionOrder.getMemberId(), MemberDTO.class);
                ResponseDTO responseDTO = serviceUtil.getDTO(url);
                if(responseDTO != null && responseDTO.getContents() != null) {
                    MemberDTO memberDTO = (MemberDTO) responseDTO.getContents();
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
        } else {
            return null;
        }
    }

    public TransactionOrder mapDTOToEntity(OrderDTO orderDTO) {
        if(orderDTO != null) {
            TransactionOrder order = new TransactionOrder();
            order.setId(orderDTO.getId() != null ? Long.parseLong(orderDTO.getId()) : null);
            order.setOrderNo(orderDTO.getOrderNo());
            order.setStatus(orderDTO.getStatus());
            order.setTimestamp(orderDTO.getTimestamp() != null ? Timestamp.valueOf(orderDTO.getTimestamp()) : null);

            if(orderDTO.getMemberName() != null) {
                String url = accountBaseUrl+apiMemberName+orderDTO.getMemberName();
//            MemberDTO memberDTO = restTemplate.getForObject("http://localhost:8112/api/member/name/" + orderDTO.getMemberName(), MemberDTO.class);
                ResponseDTO responseDTO = serviceUtil.getDTO(url);
                if(responseDTO != null && responseDTO.getContents() != null) {
                    MemberDTO memberDTO = (MemberDTO) responseDTO.getContents();
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
        } else {
            return null;
        }
    }
}
