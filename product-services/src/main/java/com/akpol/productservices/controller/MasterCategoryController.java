package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.CategoryDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.MasterCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class MasterCategoryController {
    private MasterCategoryService masterCategoryService;
    public MasterCategoryController(MasterCategoryService masterCategoryService) {
        this.masterCategoryService = masterCategoryService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<CategoryDTO> categoryDTOList = masterCategoryService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(categoryDTOList != null && categoryDTOList.size() > 0) {
            responseDTO.setContents(categoryDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        CategoryDTO categoryDTO = masterCategoryService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(categoryDTO != null) {
            responseDTO.setContents(categoryDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody CategoryDTO categoryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterCategoryService.saveOrUpdate(categoryDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody CategoryDTO categoryDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        CategoryDTO existingCategoryDTO = masterCategoryService.getById(categoryDTO.getId());
        if(existingCategoryDTO != null) {
            String result = masterCategoryService.saveOrUpdate(categoryDTO);
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

        CategoryDTO existingCategoryDTO = masterCategoryService.getById(id);
        if(existingCategoryDTO != null) {
            String result = masterCategoryService.deleteById(id);
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
