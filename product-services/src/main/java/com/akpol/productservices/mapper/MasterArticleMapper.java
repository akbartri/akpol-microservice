package com.akpol.productservices.mapper;

import com.akpol.commons.model.MasterArticle;
import com.akpol.commons.model.MasterSupplier;
import com.akpol.commons.model.dto.ArticleDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MasterArticleMapper {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<ArticleDTO> mapEntityListToDTOList(List<MasterArticle> masterArticleList) {
        return masterArticleList.stream().map(dataArticle -> {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(dataArticle.getId() != null ? dataArticle.getId().toString() : null);
            articleDTO.setTitle(dataArticle.getTitle());
            articleDTO.setShortDescription(dataArticle.getShortDescription());
            articleDTO.setLongDescription(dataArticle.getLongDescription());
            articleDTO.setActive(dataArticle.isActive() != null ? dataArticle.isActive() ? "true" : "false" : null);

            if(dataArticle.getStartDate() != null) {
                articleDTO.setStartDate(dataArticle.getStartDate().format(formatter));
            }

            if(dataArticle.getStartDate() != null) {
                articleDTO.setEndDate(dataArticle.getEndDate().format(formatter));
            }

            return articleDTO;
        }).collect(Collectors.toList());
    }

    public List<MasterArticle> mapDTOListToEntityList(List<ArticleDTO> articleDTOList) {
        return articleDTOList.stream().map(dataArticleDTO -> {
            MasterArticle article = new MasterArticle();
            article.setId(dataArticleDTO.getId() != null ? Long.parseLong(dataArticleDTO.getId()) : null);
            article.setTitle(dataArticleDTO.getTitle());
            article.setShortDescription(dataArticleDTO.getShortDescription());
            article.setLongDescription(dataArticleDTO.getLongDescription());
            article.setActive(dataArticleDTO.getActive() != null ? dataArticleDTO.getActive().equalsIgnoreCase("true") : null);

            if(dataArticleDTO.getStartDate() != null) {
                article.setStartDate(LocalDateTime.parse(dataArticleDTO.getStartDate(), formatter));
            }

            if(dataArticleDTO.getEndDate() != null) {
                article.setEndDate(LocalDateTime.parse(dataArticleDTO.getEndDate(), formatter));
            }

            return article;
        }).collect(Collectors.toList());
    }

    public ArticleDTO mapEntityToDTO(MasterArticle masterArticle) {
        if(masterArticle != null) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(masterArticle.getId() != null ? masterArticle.getId().toString() : null);
            articleDTO.setTitle(masterArticle.getTitle());
            articleDTO.setShortDescription(masterArticle.getShortDescription());
            articleDTO.setLongDescription(masterArticle.getLongDescription());
            articleDTO.setActive(masterArticle.isActive() != null ? masterArticle.isActive() ? "true" : "false" : null);

            if(masterArticle.getStartDate() != null) {
                articleDTO.setStartDate(masterArticle.getStartDate().format(formatter));
            }

            if(masterArticle.getStartDate() != null) {
                articleDTO.setEndDate(masterArticle.getEndDate().format(formatter));
            }

            return articleDTO;
        } else {
            return null;
        }
    }

    public MasterArticle mapDTOToEntity(ArticleDTO articleDTO) {
        if(articleDTO != null) {
            MasterArticle article = new MasterArticle();
            article.setId(articleDTO.getId() != null ? Long.parseLong(articleDTO.getId()) : null);
            article.setTitle(articleDTO.getTitle());
            article.setShortDescription(articleDTO.getShortDescription());
            article.setLongDescription(articleDTO.getLongDescription());
            article.setActive(articleDTO.getActive() != null ? articleDTO.getActive().equalsIgnoreCase("true") : null);

            if(articleDTO.getStartDate() != null) {
                article.setStartDate(LocalDateTime.parse(articleDTO.getStartDate(), formatter));
            }

            if(articleDTO.getEndDate() != null) {
                article.setEndDate(LocalDateTime.parse(articleDTO.getEndDate(), formatter));
            }

            return article;
        } else {
            return null;
        }
    }
}
