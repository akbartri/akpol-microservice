package com.akpol.productservices.repository;

import com.akpol.commons.model.TransactionOrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionOrderDetailRepository extends JpaRepository<TransactionOrderDetail, Long> {
//    TransactionOrder findByOrderNoEquals(String orderNo);
}
