package com.akpol.productservices.service;

import com.akpol.commons.model.dto.OrderDetailDTO;

import java.util.List;

public interface TransactionOrderDetailService {
    public List<OrderDetailDTO> getAll();
    public OrderDetailDTO getById(String id);
    public String saveOrUpdate(OrderDetailDTO orderDetailDTO);
    public String deleteById(String id);
}
