package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ArticleDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.MasterArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/article")
public class MasterArticleController {
    private MasterArticleService masterArticleService;
    public MasterArticleController(MasterArticleService masterArticleService) {
        this.masterArticleService = masterArticleService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<ArticleDTO> articleDTOList = masterArticleService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(articleDTOList != null && articleDTOList.size() > 0) {
            responseDTO.setContents(articleDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ArticleDTO articleDTO = masterArticleService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(articleDTO != null) {
            responseDTO.setContents(articleDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody ArticleDTO articleDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterArticleService.saveOrUpdate(articleDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ArticleDTO articleDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ArticleDTO existingArticleDTO = masterArticleService.getById(articleDTO.getId());
        if(existingArticleDTO != null) {
            String result = masterArticleService.saveOrUpdate(articleDTO);
            if(result.equalsIgnoreCase("success")) {
                responseDTO.setStatus("1");
                responseDTO.setMessage("success");
            }
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ArticleDTO existingArticleDTO = masterArticleService.getById(id);
        if(existingArticleDTO != null) {
            String result = masterArticleService.deleteById(id);
            if(result.equalsIgnoreCase("success")) {
                responseDTO.setStatus("1");
                responseDTO.setMessage("success");
            }
        } else {
            responseDTO.setMessage("data not found");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }
}
