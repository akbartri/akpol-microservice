package com.akpol.productservices.service;

import com.akpol.commons.model.dto.ArticleDTO;

import java.util.List;

public interface MasterArticleService {
    public List<ArticleDTO> getAll();
    public ArticleDTO getById(String id);
    public String saveOrUpdate(ArticleDTO articleDTO);
    public String deleteById(String id);
}
