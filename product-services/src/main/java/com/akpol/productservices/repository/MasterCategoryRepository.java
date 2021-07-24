package com.akpol.productservices.repository;

import com.akpol.commons.model.MasterCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCategoryRepository extends JpaRepository<MasterCategory, Long> {
    MasterCategory findByNameEquals(String categoryName);
}
