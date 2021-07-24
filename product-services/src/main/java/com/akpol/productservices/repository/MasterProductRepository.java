package com.akpol.productservices.repository;

import com.akpol.commons.model.MasterProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterProductRepository extends JpaRepository<MasterProduct, Long> {
    MasterProduct findByNameEquals(String productName);
}
