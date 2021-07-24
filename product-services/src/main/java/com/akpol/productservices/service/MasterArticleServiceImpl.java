package com.akpol.productservices.service;

import com.akpol.commons.model.MasterArticle;
import com.akpol.commons.model.dto.ArticleDTO;
import com.akpol.productservices.mapper.MasterArticleMapper;
import com.akpol.productservices.repository.MasterArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class MasterArticleServiceImpl implements MasterArticleService {
    private MasterArticleRepository masterArticleRepository;
    private MasterArticleMapper masterArticleMapper;
    public MasterArticleServiceImpl(
            MasterArticleRepository masterArticleRepository,
            MasterArticleMapper masterArticleMapper
    ) {
        this.masterArticleRepository = masterArticleRepository;
        this.masterArticleMapper = masterArticleMapper;
    }

    @Override
    public List<ArticleDTO> getAll() {
        return masterArticleMapper.mapEntityListToDTOList(masterArticleRepository.findAll());
    }

    @Override
    public ArticleDTO getById(String id) {
        return masterArticleMapper.mapEntityToDTO(masterArticleRepository.getById(Long.parseLong(id)));
    }

    @Override
    public String saveOrUpdate(ArticleDTO articleDTO) {
        MasterArticle masterArticle = masterArticleMapper.mapDTOToEntity(articleDTO);
        MasterArticle resultSaveOrUpdate = masterArticleRepository.save(masterArticle);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        MasterArticle existingMasterArticle = masterArticleRepository.getById(Long.parseLong(id));
        if(existingMasterArticle != null) {
            masterArticleRepository.delete(existingMasterArticle);
            return "success";
        } else {
            return "error";
        }
    }
}
