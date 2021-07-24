package com.akpol.productservices.repository;

import com.akpol.commons.model.TransactionPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionPaymentRepository extends JpaRepository<TransactionPayment, Long> {
//    TransactionOrder findByOrderNoEquals(String orderNo);
    TransactionPayment findByPaymentNameEquals(String paymentName);
}
