package com.akpol.productservices.repository;

import com.akpol.commons.model.MasterProductDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterProductDetailRepository extends JpaRepository<MasterProductDetail, Long> {
}
