package com.akpol.productservices.controller;

import com.akpol.commons.model.dto.ProductDetailDTO;
import com.akpol.commons.model.dto.ResponseDTO;
import com.akpol.productservices.service.MasterProductDetailService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-detail")
public class MasterProductDetailController {
    private MasterProductDetailService masterProductDetailService;
    public MasterProductDetailController(MasterProductDetailService masterProductDetailService) {
        this.masterProductDetailService = masterProductDetailService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO> findAll() {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        List<ProductDetailDTO> productDetailDTOList = masterProductDetailService.getAll();
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(productDetailDTOList != null && productDetailDTOList.size() > 0) {
            responseDTO.setContents(productDetailDTOList);
        }

        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable String id) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ProductDetailDTO productDetailDTO = masterProductDetailService.getById(id);
        responseDTO.setStatus("1");
        responseDTO.setMessage("success");
        if(productDetailDTO != null) {
            responseDTO.setContents(productDetailDTO);
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> save(@RequestBody ProductDetailDTO productDetailDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        String result = masterProductDetailService.saveOrUpdate(productDetailDTO);
        if(result.equalsIgnoreCase("success")) {
            responseDTO.setStatus("1");
            responseDTO.setMessage("success");
        }

        return new ResponseEntity<>(responseDTO,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResponseDTO> update(@RequestBody ProductDetailDTO productDetailDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setStatus("0");
        responseDTO.setMessage("error");

        ProductDetailDTO existingProductDetailDTO = masterProductDetailService.getById(productDetailDTO.getId());
        if(existingProductDetailDTO != null) {
            String result = masterProductDetailService.saveOrUpdate(productDetailDTO);
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

        ProductDetailDTO existingProductDetailDTO = masterProductDetailService.getById(id);
        if(existingProductDetailDTO != null) {
            String result = masterProductDetailService.deleteById(id);
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
