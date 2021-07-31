package com.akpol.productservices.service;

import com.akpol.commons.model.MasterArticle;
import com.akpol.commons.model.dto.ArticleDTO;
import com.akpol.productservices.mapper.MasterArticleMapper;
import com.akpol.productservices.repository.MasterArticleRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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
        return masterArticleRepository.findById(Long.parseLong(id)).map(masterArticle -> masterArticleMapper.mapEntityToDTO(masterArticle)).orElse(null);
    }

    @Override
    public String saveOrUpdate(ArticleDTO articleDTO) {
        MasterArticle masterArticle = masterArticleMapper.mapDTOToEntity(articleDTO);
        MasterArticle resultSaveOrUpdate = masterArticleRepository.save(masterArticle);
        return resultSaveOrUpdate.getId() != null ? "success" : "error";
    }

    @Override
    public String deleteById(String id) {
        Optional<MasterArticle> existingMasterArticle = masterArticleRepository.findById(Long.parseLong(id));
        if(existingMasterArticle.isPresent()) {
            masterArticleRepository.delete(existingMasterArticle.get());
            return "success";
        } else {
            return "error";
        }
    }
}
