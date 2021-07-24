package com.akpol.productservices.repository;

import com.akpol.commons.model.TransactionShipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionShipmentRepository extends JpaRepository<TransactionShipment, Long> {
//    TransactionOrder findByOrderNoEquals(String orderNo);
}
