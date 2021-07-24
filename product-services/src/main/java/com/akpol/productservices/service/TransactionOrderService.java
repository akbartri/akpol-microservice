package com.akpol.productservices.service;

import com.akpol.commons.model.dto.OrderDTO;

import java.util.List;

public interface TransactionOrderService {
    public List<OrderDTO> getAll();
    public OrderDTO getById(String id);
    public String saveOrUpdate(OrderDTO orderDTO);
    public String deleteById(String id);
}
