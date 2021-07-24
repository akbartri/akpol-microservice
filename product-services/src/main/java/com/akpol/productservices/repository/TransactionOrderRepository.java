package com.akpol.productservices.repository;

import com.akpol.commons.model.TransactionOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOrderRepository extends JpaRepository<TransactionOrder, Long> {
    TransactionOrder findByOrderNoEquals(String orderNo);
}
