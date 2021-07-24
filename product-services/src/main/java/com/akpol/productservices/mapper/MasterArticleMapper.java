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
            articleDTO.setId(dataArticle.getId().toString());
            articleDTO.setTitle(dataArticle.getTitle());
            articleDTO.setShortDescription(dataArticle.getShortDescription());
            articleDTO.setLongDescription(dataArticle.getLongDescription());
            articleDTO.setActive(dataArticle.isActive() ? "true" : "false");

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
            article.setId(Long.parseLong(dataArticleDTO.getId()));
            article.setTitle(dataArticleDTO.getTitle());
            article.setShortDescription(dataArticleDTO.getShortDescription());
            article.setLongDescription(dataArticleDTO.getLongDescription());
            article.setActive(dataArticleDTO.getActive().equalsIgnoreCase("true"));

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
        ArticleDTO articleDTO = new ArticleDTO();
        articleDTO.setId(masterArticle.getId().toString());
        articleDTO.setTitle(masterArticle.getTitle());
        articleDTO.setShortDescription(masterArticle.getShortDescription());
        articleDTO.setLongDescription(masterArticle.getLongDescription());
        articleDTO.setActive(masterArticle.isActive() ? "true" : "false");

        if(masterArticle.getStartDate() != null) {
            articleDTO.setStartDate(masterArticle.getStartDate().format(formatter));
        }

        if(masterArticle.getStartDate() != null) {
            articleDTO.setEndDate(masterArticle.getEndDate().format(formatter));
        }

        return articleDTO;
    }

    public MasterArticle mapDTOToEntity(ArticleDTO articleDTO) {
        MasterArticle article = new MasterArticle();
        article.setId(Long.parseLong(articleDTO.getId()));
        article.setTitle(articleDTO.getTitle());
        article.setShortDescription(articleDTO.getShortDescription());
        article.setLongDescription(articleDTO.getLongDescription());
        article.setActive(articleDTO.getActive().equalsIgnoreCase("true"));

        if(articleDTO.getStartDate() != null) {
            article.setStartDate(LocalDateTime.parse(articleDTO.getStartDate(), formatter));
        }

        if(articleDTO.getEndDate() != null) {
            article.setEndDate(LocalDateTime.parse(articleDTO.getEndDate(), formatter));
        }

        return article;
    }
}
