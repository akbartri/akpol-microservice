package com.akpol.productservices.repository;

import com.akpol.commons.model.MasterArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterArticleRepository extends JpaRepository<MasterArticle, Long> {
}
