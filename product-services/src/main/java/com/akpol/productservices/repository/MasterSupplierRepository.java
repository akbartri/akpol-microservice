package com.akpol.productservices.repository;

import com.akpol.commons.model.MasterSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterSupplierRepository extends JpaRepository<MasterSupplier, Long> {
    MasterSupplier findByNameEquals(String supplierName);
}
